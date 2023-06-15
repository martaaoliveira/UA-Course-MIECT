%%4a
%%ber=0

Y=1000; %% pps
C=10; %%capacity

numeroelementos=(109-65+1)+(1517-111+1); %retirar elementos que têm equiprobilidade
probabilidade_elementos= (100-(19+23+17))/numeroelementos; %probabilidade dos elementos que sao equiprovaveis


a=65:109;
a=a*(probabilidade_elementos/100);
b=111:1517;
b=b*(probabilidade_elementos/100);

numMedioBytes=0.19*64 + 0.23*110 + 0.17*1518+sum(a)+sum(b);
tempoMedio=(numMedioBytes*8)/(C*10^6);

fprintf("Average packet size: %.2f Bytes\nAverage packet transmission time: %.2e seconds\n\n",numMedioBytes ,tempoMedio );

%%duvidas 4b --confirmar
%%4b the average throughput (in Mbps) of the IP flow; answer: 4.96 Mbps 
throughput=Y*numMedioBytes*8*10^-6;%% pps
fprintf('The average throughput (in Mbps) of the IP flow is: %.4f Mbps\n', throughput);

%%4c
capacity=C*10^6/(numMedioBytes*8);
fprintf('The capacity of the link is: %.2f pps\n', capacity);

%%4d %slide 53 duvida qual formula usar!-- confirmar
delay=10*10e-6;
bytes=64:1518;
%64bytes/capacidade do link 
S=(bytes.*8)./C*10^6;
S2=(bytes.*8)./C*10^6;

for i=1:length(bytes)
    if i==1
        S(i)=S(i)*0.19;
        S2(i)=S2(i)^2*0.19;
    elseif i==110-64+1
        S(i)=S(i)*0.23;
        S2(i)=S2(i)^2*0.23;
    elseif i==1518-64+1
        S(i)=S(i)*0.17;
        S2(i)=S2(i)^2*0.17;
    else
        S(i)=S(i)*(probabilidade_elementos/100);
        S2(i)=S2(i)^2*(probabilidade_elementos/100);
    end
end

ES = sum(S);
ES2 = sum(S2);

queuing = Y*ES2 / (2*(1-Y*ES));
propagation_delay = 10*10^-6;


system = queuing + tempoMedio + propagation_delay;

fprintf("Queuing: %.2e seconds\nSystem packet delay: %.2e\n\n", queuing, system);

%%4e-- confirmar 
lambdas = 100:2000;

queue = lambdas.*ES2 ./ (2*(1-lambdas*ES));
systemd = queue + tempoMedio + propagation_delay;

figure(1);
plot(lambdas, systemd);
title("Average system delay (seconds)");
xlabel("{\lambda} (pps)")
grid on;

%%4f
capacities = [10*10^6 20*10^6 100*10^6];
y1 = 100:2000;
y2 = 200:4000;
y3 = 1000:20000;

x1 = (y1 ./ (capacities(1) / (numMedioBytes * 8))) * 100;
x2 = (y2 ./ (capacities(2) / (numMedioBytes * 8))) * 100;
x3 = (y3 ./ (capacities(3) / (numMedioBytes * 8))) * 100;

S1 = (bytes .* 8) ./ capacities(1);
S12 = (bytes .* 8) ./ capacities(1);
S2 = (bytes .* 8) ./ capacities(2);
S22 = (bytes .* 8) ./ capacities(2);
S3 = (bytes .* 8) ./ capacities(3);
S32 = (bytes .* 8) ./ capacities(3);

for i = 1:length(bytes)
    if i == 1
        S1(i) = S1(i) * 0.19;
        S12(i) = S12(i)^2 * 0.19;
        S2(i) = S2(i) * 0.19;
        S22(i) = S22(i)^2 * 0.19;
        S3(i) = S3(i) * 0.19;
        S32(i) = S32(i)^2 * 0.19;
    elseif i == 110-64+1
        S1(i) = S1(i) * 0.23;
        S12(i) = S12(i)^2 * 0.23;
        S2(i) = S2(i) * 0.23;
        S22(i) = S22(i)^2 * 0.23;
        S3(i) = S3(i) * 0.23;
        S32(i) = S32(i)^2 * 0.23;
    elseif i == 1518-64+1
        S1(i) = S1(i) * 0.17;
        S12(i) = S12(i)^2 * 0.17;
        S2(i) = S2(i) * 0.17;
        S22(i) = S22(i)^2 * 0.17;
        S3(i) = S3(i) * 0.17;
        S32(i) = S32(i)^2 * 0.17;
    else
        S1(i) = S1(i) * probabilidade_elementos/100;
        S12(i) = S12(i)^2 * probabilidade_elementos/100;
        S2(i) = S2(i) * probabilidade_elementos/100;
        S22(i) = S22(i)^2 * probabilidade_elementos/100;
        S3(i) = S3(i) * probabilidade_elementos/100;
        S32(i) = S32(i)^2 * probabilidade_elementos/100;
    end
end

wq1 = y1 .* sum(S12) ./ (2.*(1 - y1 .* sum(S1)));
wq2 = y2 .* sum(S22) ./ (2.*(1 - y2 .* sum(S2)));
wq3 = y3 .* sum(S32) ./ (2.*(1 - y3 .* sum(S3)));

avg_times = zeros(1, 3);
for i=1:3
    avg_times(i) = (numMedioBytes * 8) / capacities(i);
end

sys1 = wq1 + avg_times(1) + delay;
sys2 = wq2 + avg_times(2) + delay;
sys3 = wq3 + avg_times(3) + delay;

figure(2);
plot(x1, sys1, 'b', x2, sys2, 'r', x3, sys3, 'g');
title("Average system delay (seconds)");
legend('C = 10 Mbps','C = 20 Mbps','C = 100 Mbps', 'location','northwest');
xlabel("{\lambda} (% of the link capability)")
grid on;


