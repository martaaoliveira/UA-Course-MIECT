%%5a

%%lambda=1800;
%%c=10;
%%f=2000;
%%p=6;

%Simulator1(lambda,c,f,p);

N=10;
lambda=1800;
p=10000;
alfa=0.1;
c=10;
f=1000000;

PL= zeros(1,N);
PD= zeros(1,N);
MPD=zeros(1,N);
TP=zeros(1,N);

for it=1:N
    [PL(it),PD(it),MPD(it),TP(it)]= Simulator1(lambda,c,f,p);
end
fprintf('5a\n')
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
fprintf('Max. Packetdelay = %.2e +-%.2e\n\n',media,term)


