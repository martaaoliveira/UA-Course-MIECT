
%%estado 0->estado 1->....
%%3a
states = [10^-6 10^-5 10^-4 10^-3 10^-2];
%%formula slide 39
p0=1/(1+(8/600)+((8/600)*(5/100)));

p1=(p0*(8/(600*100)))*100;

p2=p0*((8*5)/(600*100))*100;

p3=(p0*((8*5*2)/(600*100*20)))*100;

p4=(p0*((8*5*2*1)/(600*100*20*5)))*100;

fprintf("Probability of link being in each of the five states\n")
fprintf("Estado 10^-6: %.2e\n", p0);
fprintf("Estado 10^-5: %.2e\n", p1);
fprintf("Estado 10^-4: %.2e\n", p2);
fprintf("Estado 10^-3: %.2e\n", p3);
fprintf("Estado 10^-5: %.2e\n\n", p4);

%%3b - a e b sao propriedades equivalentes
%%resposta slide 34: A probabilidade pij pode ser interpretada como a proporção de tempo
%%em que o processo está no estado j
fprintf(['Pelas propriedades das cadeias de Markov, a probabilidade de um link estar num determinado estado é igual à percentagem de tempo médio que cada link fica ness estado\n\n']);
%%3c

avgber = (10e-6)*p0 + (10e-5)*p1 + (10e-4)*p2 + (10e-3)*p3 + (10e-2)*p4;
fprintf("Average BER of the link: %.2e\n\n", avgber);


%%3d

t0=(1/8)*60;

t1=(1/(5+600))*60;

t2=(1/(2+100))*60;

t3=(1/(1+20))*60;

t4=1/(5)*60;

fprintf("Average time duration that the link stays in each state\n")
fprintf("Estado 10^-6: %1.2f minutos\n", t0);
fprintf("Estado 10^-5: %1.2f minutos\n", t1);
fprintf("Estado 10^-4: %1.2f minutos\n", t2);
fprintf("Estado 10^-3: %1.2f minutos\n", t3);
fprintf("Estado 10^-2: %1.2f minutos\n\n", t4);

%%3e 
pinterf=p3+p4;
pnormal=p0+p1+p2;

fprintf("Probability of normal state: %.6f \n", pnormal);
fprintf("Probability of interference state: %.2e \n\n", pinterf);


%%3f
bern = ((10^-6)*p0 + (10^-5)*p1 + (10^-4)*p2) / pnormal;
beri = ((10^-3)*p3 + (10^-2)*p4) / pinterf;

fprintf("Average ber when normal state: %.2e \n", bern);
fprintf("Average ber when interference state: %.2e \n", beri);

%%3g: ver slide 13
x=64:1500;
p_err_1=1 - (((1-(10^-6)).^(x.*8))); %-> estado 0
p_err_2= 1 - (((1-(10^-5)).^(x.*8)));% estado 1...
p_err_3=1 - (((1-(10^-4)).^(x.*8)));
p_err_4=1 - (( (1-(10^-3)).^(x.*8)));
p_err_5= 1 - (((1-(10^-2)).^(x.*8 )));
prob=p_err_1*p0 + p_err_2*p1 + p_err_3.*p2 + p_err_4.*p3 + p_err_5.*p4; 

figure(1)
plot(x,prob)
title("Prob. of at least one error")
xlabel("B (Bytes)")
grid on

%%3h
%%prob estado normal=pnormal=p0+p1+p2;
%%1-probabilidade sem erros 
p_errs1 = 1 - (1 - 10^-6).^(x.*8); %%evitamos de fazer nchoosek pq de 0 é sempre 1, nao gastamos memoria do pc (smort i know)
p_errs2 = 1 - (1 - 10^-5).^(x.*8);
p_errs3 = 1 - (1 - 10^-4).^(x.*8);
p_errs4 = 1 - (1 - 10^-3).^(x.*8);
p_errs5 = 1 - (1 - 10^-2).^(x.*8);
pnormal_plot = (p_errs1.*p0 + p_errs2.*p1 + p_errs3.*p2) ./ (p_errs1.*p0 + p_errs2.*p1 + p_errs3.*p2 + p_errs4.*p3 + p_errs5.*p4);


figure(2)
plot(x, pnormal_plot)
title("Prob. of Normal State")
xlabel("B (Bytes)")
%%axis([0 1500 0.93 1])
grid on


%%3i
%%pinterf=p3+p4;
p_n1 = (1 - 10^-6).^(x.*8);
p_n2 = (1 - 10^-5).^(x.*8);
p_n3 = (1 - 10^-4).^(x.*8);
p_n4 = (1 - 10^-3).^(x.*8);
p_n5 = (1 - 10^-2).^(x.*8);

pint_plot = (p_n4.*p3 + p_n5.*p4) ./ (p_n1.*p0 + p_n2.*p1 + p_n3.*p2 + p_n4.*p3 + p_n5.*p4);

figure(3)
semilogy(x, pint_plot)
title("Prob. of Interference State")
xlabel("B (Bytes)")
grid on













