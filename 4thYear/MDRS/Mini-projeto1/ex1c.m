
%%ex1c
packet_size = (64:1518); 
P1 = zeros(1,length(packet_size));
P2 = zeros(1,length(packet_size));
b1 = 10^(-6); %bit error rate
b2 = 10^(-4);
numeroelementos=(109-65+1)+(1517-111+1); %retirar elementos que não têm equiprobilidade
probabilidade_elementos= (1-(0.19+0.23+0.17))/numeroelementos; %probabilidade dos elementos que sao equiprovaveis

for i = 1: length(packet_size)
    if packet_size(i) == 64
        P1(i)= (1-((1-b1).^(packet_size(i)*8))).*0.19;
        P2(i)=(1-((1-b2).^(packet_size(i)*8))).*0.19;
    elseif packet_size(i) == 110
        P1(i)= (1-((1-b1).^(packet_size(i)*8))).*0.23;
        P2(i)=(1-((1-b2).^(packet_size(i)*8))).*0.23;
    elseif packet_size(i) == 1518
        P1(i)= (1-((1-b1).^(packet_size(i)*8))).*0.17;
        P2(i)=(1-((1-b2).^(packet_size(i)*8))).*0.17;

    else
        P1(i)= (1-((1-b1).^(packet_size(i)*8))).*probabilidade_elementos;
        P2(i)=(1-((1-b2).^(packet_size(i)*8))).*probabilidade_elementos;
    end
end
fprintf('Average Packet Loss (ber: 10^(-6)): %.2e\n',sum(P1)*100);
fprintf('Average Packet Loss (ber: 10^(-4)): %.2e\n',sum(P2)*100);