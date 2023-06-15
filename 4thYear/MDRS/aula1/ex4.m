%%4a
Y=1000;
C=10;
numeroelementos=(109-65+1)+(1517-11+1); %retirar elementos que têm equiprobilidade
probabilidade_elementos= (100-(19+23+17))/numeroelementos; %probabilidade dos elementos que sao equiprovaveis


a=65:109;
a=a*(probabilidade_elementos/100);
b=111:1517;
b=b*(probabilidade_elementos/100);

numMedioBytes=0.19*64 + 0.23*110 + 0.17*1518+sum(a)+sum(b);
tempoMedio=(numMedioBytes*8)/(C*10^6);

fprintf("Average packet size: %.2f Bytes\nAverage packet transmission time: %.2e seconds\n\n",numMedioBytes ,tempoMedio );

%%4b
avg_thr=Y*numMedioBytes;%% pps*b

%%4c
capacity=C/numMedioBytes;

%%4d %slide 60 e 53
delay=10*10e-6;
queuing;

bytes=64:1518;

S=(bytes.*8)./C*10e6;

for i=1:length(bytes)
    if i==1
        S(i)=S(i)*0.19;
    elseif i==110-64+1
        S(i)=S(i)*0.23;
    elseif i==1518-64+1
        S(i)=S(i)*0.17;
    else
        S(i)=S(i)*(probabilidade_elementos/100)
    end
end
system = queuing + tmpMedio + delay;





