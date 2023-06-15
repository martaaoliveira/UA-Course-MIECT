packet_size = (64:1518);
VoIP_size = (109:130);
P = zeros(1,length(packet_size));
P_VoIP = zeros(1,length(packet_size));
b = 10^(-5);
numeroelementos=(109-65+1)+(1517-111+1); %retirar elementos que não têm equiprobilidade
probabilidade_elementos= (1-(0.19+0.23+0.17))/numeroelementos; %probabilidade dos elementos que nao sao equiprovaveis
for i = 1: length(packet_size)
    if packet_size(i) == 64
       P(i) = (1 - ((1-b).^(packet_size(i)*8))).*0.19;
    elseif packet_size(i) == 110
       P(i) =(1 - ((1-b).^(packet_size(i)*8))).*0.23;
    elseif packet_size(i) == 1518
       P(i) = (1 - ((1-b).^(packet_size(i)*8))).*0.17;
    else
       P(i) = (1 - ((1-b).^(packet_size(i)*8))).*probabilidade_elementos;
    end
end

for i = 1:length(VoIP_size)
   P_VoIP(i) = (1 - ((1-b).^(packet_size(i)*8)))*((1/length(VoIP_size))); 
end

fprintf('Average Packet Loss in Data (ber: 10^(-5)): %.2e\n',sum(P)*100);
fprintf('Average Packet Loss in VoiP (ber: 10^(-5)): %.2e\n',sum(P_VoIP)*100);