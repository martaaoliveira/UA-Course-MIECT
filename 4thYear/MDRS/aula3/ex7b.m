%  lambda - packet rate (packets/sec)
%  C      - link bandwidth (Mbps)
%  f      - queue size (Bytes)
%  P      - number of packets (stopping criterium)

lambda = 1800;
C =10;
f =10^6;
P =10000;
n = 20;

N = 100;
PLdata = zeros(1,N);
PLvoip = zeros(1,N);
APDdata = zeros(1,N);
APDvoip = zeros(1,N);
MPDdata = zeros(1,N);
MPDvoip = zeros(1,N);
TT = zeros(1,N);

for it = 1:N
    [PLdata(it), APDdata(it), MPDdata(it), PLvoip(it), APDvoip(it), MPDvoip(it), TT(it)] = Simulator3(lambda,C,f,P,n);
end

alfa = 0.1; %90% confidence interval
media = mean(PLdata);
term = norminv(1-alfa/2)*sqrt(var(PLdata)/N);
fprintf('PacketLoss of data (percent) = %.2e +- %.2e\n',media,term)
media = mean(PLvoip);
term = norminv(1-alfa/2)*sqrt(var(PLvoip)/N);
fprintf('PacketLoss of VoIP (percent) = %.2e +- %.2e\n',media,term)
media = mean(APDdata);
term = norminv(1-alfa/2)*sqrt(var(APDdata)/N);
fprintf('Av. Packet Delay of data (ms) = %.2e +- %.2e\n',media,term)
media = mean(APDvoip);
term = norminv(1-alfa/2)*sqrt(var(APDvoip)/N);
fprintf('Av. Packet Delay of voip (ms) = %.2e +- %.2e\n',media,term)
media = mean(MPDdata);
term = norminv(1-alfa/2)*sqrt(var(MPDdata)/N);
fprintf('Max Packet Delay of data (ms) = %.2e +- %.2e\n',media,term)
media = mean(MPDvoip);
term = norminv(1-alfa/2)*sqrt(var(MPDvoip)/N);
fprintf('Max Packet Delay of voip (ms) = %.2e +- %.2e\n',media,term)
media = mean(TT);
term = norminv(1-alfa/2)*sqrt(var(TT)/N);
fprintf('Throughput (Mbs) = %.2e +- %.2e\n\n',media,term)