%%5c

N=100;
lambda=1800;
p=10000;
alfa=0.1;
c=10;
f=100000;
PL= zeros(1,N);
PD= zeros(1,N);
MPD=zeros(1,N);
TP=zeros(1,N);
b=10^-6;
for it=1:N
    [PL(it),PD(it),MPD(it),TP(it)]= Simulator2(lambda,c,f,p,b);
end

media=mean(PL);
term = norminv(1-alfa/2)*sqrt(var(PL)/N);
fprintf('packetLoss = %.2e +-%.2e\n',media,term)
media = mean(PD);
term = norminv(1-alfa/2)*sqrt(var(PD)/N);
fprintf('packetDelay = %.2e +-%.2e\n',media,term)

media = mean(MPD);
term = norminv(1-alfa/2)*sqrt(var(MPD)/N);
fprintf('Max. Packetdelay = %.2e +-%.2e\n',media,term)

media = mean(TP);
term = norminv(1-alfa/2)*sqrt(var(TP)/N);
fprintf('Max. Packetdelay = %.2e +-%.2e\n',media,term)