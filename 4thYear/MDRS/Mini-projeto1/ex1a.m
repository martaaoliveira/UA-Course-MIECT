%  lambda - packet rate (packets/sec)
%  C      - link bandwidth (Mbps)
%  f      - queue size (Bytes)
%  P      - number of packets (stopping criterium)
%  b      - BER (Bit Error Rate) 

lambda = [1500, 1600, 1700, 1800, 1900];
C =10;
f =10^6;
P =100000;
b=10^-6;
N = 20;

AvgPL= zeros(1,length(lambda));
PLerror= zeros(1,length(lambda));
AvgPD= zeros(1,length(lambda));
APDerror= zeros(1,length(lambda));

for i = 1:length(lambda)
    PL = zeros(1,N);
    APD = zeros(1,N);
    MPD = zeros(1,N);
    TT = zeros(1,N);

    for it = 1:N
        [PL(it), APD(it), MPD(it), TT(it)] = Simulator2(lambda(i),C,f,P,b);
    end

    alfa = 0.1; %90% confidence interval
    AvgPL(i) = mean(PL);
    PLerror(i) = norminv(1-alfa/2)*sqrt(var(PL)/N);
    
    AvgPD(i) = mean(APD);
    APDerror(i) = norminv(1-alfa/2)*sqrt(var(APD)/N);
end

%%
figure(1)
bar(lambda,AvgPL)
hold on
er = errorbar(lambda,AvgPL,PLerror);    
er.Color = [0 0 0];                            
er.LineStyle = 'none';  
xlabel('lambda (pps)');
ylabel('Average Packet Loss (%)');
title('Average Packet Loss Results');
ylim([0 0.53])
hold off

figure(2)
bar(lambda,AvgPD)
hold on
er = errorbar(lambda,AvgPD,APDerror);    
er.Color = [0 0 0];                            
er.LineStyle = 'none';  
xlabel('lambda (pps)');
ylabel('Average Packet Delay (ms)');
title('Average Packet Delay Results');
hold off
