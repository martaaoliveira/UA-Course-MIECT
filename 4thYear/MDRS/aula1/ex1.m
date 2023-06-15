% E – o aluno responde corretamente
% F1 – o aluno sabe a resposta
% F2 – o aluno não sabe a resposta

% 1.a) P(E) = P(E|F1)P(F1) + P(E|F2)P(F2)
p = 0.6;
n = 4;
pE = (1*p) + (1/n * (1-p))

%1.b) P(F1|E) = P(E|F1)P(F1) / P(E)
p = 0.7;
n = 5;
pf1E = (1 * p) / (p + (1 -p)/n)


%1.c)
figure()
x = linspace(0,1,10);
y3 = x + (1/3 * (1-x));
y4 = x + (1/4 * (1-x));
y5 = x + (1/5 * (1-x));
plot(x*100,y3*100,x*100,y4*100,x*100,y5*100)
grid on
ylim([0,100])

%1.d) 
figure()
x = linspace(0,1,10);
y3 = x ./ (x + (1 - x)/3);
y4 = x ./ (x + (1 - x)/4);
y5 = x ./ (x + (1 - x)/5);
plot(x*100,y3*100,x*100,y4*100,x*100,y5*100)
grid on
