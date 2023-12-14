#include "cap2dat.h"

#define 	NUM_METRICS 6
struct ipv4_filter_list filters_ipv4 = {.valid_filters=0, .filter=NULL};
long counters[NUM_METRICS]={0};
double start_timestamp;
float delta_time=0.1;
uint allpkts=0, npkts=0, last_swnd=0;
char filename[256];

void update_metrics(long *counters, double timestamp, uint tdir, const u_char* packet)
{
	FILE * fp;
	char datfilename[256];
	uint swnd;
	
	sprintf(datfilename,"%s_%.2fs.dat",filename,delta_time);
	
	if(npkts>1)
		fp = fopen(datfilename, "a");
	else
		fp = fopen(datfilename, "w");
	if(fp==NULL)
	{
		printf("Error opening output file!\n");
		exit(-1);
	}
		
	swnd=(timestamp-start_timestamp)/delta_time;
	
	struct ipv4_header *ipv4ptr = (struct ipv4_header *) (packet+ETHER_HDR_LEN);
	uint len=ntohs(ipv4ptr->ip_len);
	uint hlen=4*((ipv4ptr->ip_vhl)&0x0f);
	uint proto=ipv4ptr->ip_p;
	uint8_t syn_flag=0;
	
	if(swnd>last_swnd)
	{
		for(uint j=0;j<NUM_METRICS;j++)
			fprintf(fp,"%ld ",counters[j]);
		fprintf(fp,"\n");
		memset(counters, 0, NUM_METRICS*sizeof(long));
	}
	
	if(swnd>last_swnd+1)
		for(uint i=0;i<swnd-last_swnd-1;i++)
		{
			for(uint j=0;j<NUM_METRICS;j++)
				fprintf(fp,"%ld ",counters[j]);
			fprintf(fp,"\n");
		}
	
	if(proto==6)
	{
		struct tcp_header *tcpptr = (struct tcp_header *) (packet+ETHER_HDR_LEN+hlen);
		syn_flag=((tcpptr->tcp_flags)&0x2)>>1;
	}
	
	if(tdir==1 || tdir==3)
	{
		counters[0]++;
		counters[1]+=len;
		if(syn_flag==1) counters[2]++;
	}
	
	if(tdir==2 || tdir==3)
	{
		counters[3]++;
		counters[4]+=len;
		if(syn_flag==1) counters[5]++;
	}
	

	last_swnd=swnd;
	fclose(fp);
	
}

void my_callback(u_char *useless,const struct pcap_pkthdr* pkthdr,const u_char* packet)
{
	double timestamp;
	timestamp=pkthdr->ts.tv_sec+(double)pkthdr->ts.tv_usec/1000000;
	
	struct ether_header *eptr;  /* net/ethernet.h */
	eptr = (struct ether_header *) packet;
	
	allpkts++;
	 /* check to see if we have an ipv4 packet */
	if (ntohs (eptr->ether_type) == ETHER_TYPE_IPv4)
	{
		npkts++;
		struct ipv4_header *ipv4ptr; 
		ipv4ptr = (struct ipv4_header *) (packet+ETHER_HDR_LEN);
		
		char ipv4_srcaddr[INET_ADDRSTRLEN],ipv4_dstaddr[INET_ADDRSTRLEN];
		
		ipv4_str_addr(ipv4ptr->ip_src,ipv4_srcaddr);
		ipv4_str_addr(ipv4ptr->ip_dst,ipv4_dstaddr);
		
		uint filt_res=addr_ipv4_filter(ipv4ptr->ip_src, ipv4ptr->ip_dst, &filters_ipv4);
		
		char tdir[4];
		if(filt_res==1)
			sprintf(tdir,"Up");
		else if(filt_res==2)
			sprintf(tdir,"Down");
		else if(filt_res==3)
			sprintf(tdir,"Both");
		else
			sprintf(tdir,"None");
		
	
		if(npkts==1) start_timestamp=timestamp;
		uint len=ntohs(ipv4ptr->ip_len);
		uint hlen=4*((ipv4ptr->ip_vhl)&0x0f);
		uint proto=ipv4ptr->ip_p;
		
		if(proto==6) //TCP
		{
			struct tcp_header * tcpptr = (struct tcp_header *) (packet+ETHER_HDR_LEN+hlen);
			uint16_t srcprt=ntohs(tcpptr->src_port);
			uint16_t dstprt=ntohs(tcpptr->dst_port);
			uint8_t syn_flag=((tcpptr->tcp_flags)&0x2)>>1;
			update_metrics(counters,timestamp,filt_res,packet);
			fprintf(stdout,"%d: %f, IPv4, len: %d, %s -> %s [%d,%d,%s], TCP (%d,%d,%x)\n",allpkts,timestamp,len,ipv4_srcaddr,ipv4_dstaddr,hlen,proto,tdir,srcprt,dstprt,syn_flag);
		}
		else
			fprintf(stdout,"%d: %f, IPv4, len: %d, %s -> %s [%d,%d,%s]\n",allpkts,timestamp,len,ipv4_srcaddr,ipv4_dstaddr,hlen,proto,tdir);

		update_metrics(counters,timestamp,filt_res,packet);
	}
	else if  (ntohs (eptr->ether_type) == ETHER_TYPE_IPv6)
		fprintf(stdout,"%d: %f, IPv6 \n",allpkts,timestamp);
	else
		fprintf(stdout,"%d: %f, not IP \n",allpkts,timestamp);
	
	
	
	fflush(stdout);
}

int main(int argc,char **argv)
{ 
    int i;
    char *dev; 
    char errbuf[PCAP_ERRBUF_SIZE];
    pcap_t* descr;
    const u_char *packet;
    struct pcap_pkthdr hdr;     /* pcap.h */
    struct ether_header *eptr;  /* net/ethernet.h */
    
    if(argc<2)
    {
		printf("Usage: cap2dat cap_filename [sample_window]\n\n");
		return -1;
	}
	else
	{
		sprintf(filename,"%s",argv[1]);
		printf("%s\n",filename);
	}
    
    if(argc==3)
		delta_time=atoi(argv[2]);
	
	printf("Sampling window: %.2f seconds\n",delta_time);
    
    uint valid_filters;
	read_ipv4_filter("local_net_IPv4.conf",&filters_ipv4);
	
	char ipv4_prefix[INET_ADDRSTRLEN],ipv4_mask[INET_ADDRSTRLEN];

	valid_filters=filters_ipv4.valid_filters;
	if(valid_filters>0){
		printf("Local Network %d\n",valid_filters);
		for(uint j=0;j<valid_filters;j++)
		{
			ipv4_str_addr(filters_ipv4.filter[j].net_prefix,ipv4_prefix);
			ipv4_str_addr(filters_ipv4.filter[j].net_mask,ipv4_mask);
			printf("\t-> %s %s\n",ipv4_prefix,ipv4_mask);		
		}
	}
	else
		printf("No IPv4 filters\n");
	printf("####\n");

    /* grab a device to peak into... */
    //dev = pcap_lookupdev(errbuf);
    //if(dev == NULL)
    //{ printf("%s\n",errbuf); exit(1); }
    /* open device for reading */
    //descr = pcap_open_live(dev,BUFSIZ,0,-1,errbuf);

	FILE * fp;
	fp = fopen(filename, "r");
    if(fp==NULL)
	{
		printf("Error opening pcap file!\n");
		exit(-1);
	}
	else
		fclose(fp);
    
    
    descr = pcap_open_offline(filename,errbuf);
    //if(descr == NULL)
    //{ printf("pcap_open_live(): %s\n",errbuf); exit(1); }

    pcap_loop(descr,0,my_callback,NULL);

    fprintf(stdout,"\nDone processing packets!\n");
    return 0;
}
