%  lambda - packet rate (packets/sec)
%  C      - link bandwidth (Mbps)
%  f      - queue size (Bytes)
%  P      - number of packets (stopping criterium)
%  n      - number of voip packet flows

lambda = 1500;
C =10;
f =10^4;
P =100000;
n = [10, 20, 30, 40];
N = 20;

AvgPL_D= zeros(1,length(n));
PLerror_D= zeros(1,length(n));
AvgPD_D= zeros(1,length(n));
APDerror_D= zeros(1,length(n));
AvgPL_V= zeros(1,length(n));
PLerror_V= zeros(1,length(n));
AvgPD_V= zeros(1,length(n));
APDerror_V= zeros(1,length(n));

for i = 1:length(n)
    PLdata = zeros(1,N);
    PLvoip = zeros(1,N);
    APDdata = zeros(1,N);
    APDvoip = zeros(1,N);
    
    for it = 1:N
        [PLdata(it), APDdata(it), ~, PLvoip(it), APDvoip(it), ~, ~] = Simulator3(lambda,C,f,P,n);
    end
    
    alfa = 0.1; %90% confidence interval
    AvgPL_D(i) = mean(PLdata);
    PLerror_D(i) = norminv(1-alfa/2)*sqrt(var(PLdata)/N);
    
    AvgPD_D(i) = mean(APDdata);
    APDerror_D(i) = norminv(1-alfa/2)*sqrt(var(APDdata)/N);
    
    AvgPL_V(i) = mean(PLvoip);
    PLerror_V(i) = norminv(1-alfa/2)*sqrt(var(PLvoip)/N);
    
    AvgPD_V(i) = mean(APDvoip);
    APDerror_V(i) = norminv(1-alfa/2)*sqrt(var(APDvoip)/N);
end
%%
figure(1)
bar(n,AvgPL_D)
hold on
er = errorbar(n,AvgPL_D,PLerror_D);    
er.Color = [0 0 0];                            
er.LineStyle = 'none';  
xlabel('n (VoIP flows)');
ylabel('Average Data Packet Loss (%)');
title('Average Data Packet Loss Results');
%ylim([0 0.53])
hold off

figure(2)
bar(n,AvgPD_D)
hold on
er = errorbar(n,AvgPD_D,APDerror_D);    
er.Color = [0 0 0];                            
er.LineStyle = 'none';  
xlabel('n (VoIP flows)');
ylabel('Average Data Packet Delay (ms)');
title('Average Data Packet Delay Results');
hold off

figure(3)
bar(n,AvgPL_V)
hold on
er = errorbar(n,AvgPL_V,PLerror_V);    
er.Color = [0 0 0];                            
er.LineStyle = 'none';  
xlabel('n (VoIP flows)');
ylabel('Average VoIP Packet Loss (%)');
title('Average VoIP Packet Loss Results');
%ylim([0 0.53])
hold off

figure(4)
bar(n,AvgPD_V)
hold on
er = errorbar(n,AvgPD_V,APDerror_V);    
er.Color = [0 0 0];                            
er.LineStyle = 'none';  
xlabel('n (VoIP flows)');
ylabel('Average VoIP Packet Delay (ms)');
title('Average VoIP Packet Delay Results');
hold off