clear all
close all
clc

load('InputDataProject2.mat')
% - Verificar situações em que o load passa dos 50 e aumentar a capacidade nesses casos para 100
%       Se passar de 100 colocar energia a infinito
% - Melhorar calculo da energia como foi feito no ex2 e melhorar tbm o greedy como foi feito neste exc (sugestao prof
% 3C - Acrescentar calculo da energia dos nós anycast que tbm têm clientes (ignorar so o calculo da energia dos links em cada fluxo)
% ex 3
anycast_Nodes = [5 12];
source_Nodes = T_any(:,1)';
nNodes= size(Nodes,1);
[~, paths] = bestCostPaths(L, source_Nodes, anycast_Nodes);

T_anyAux = T_any;
T_any = zeros(length(source_Nodes), 4);
i = 1;
for p = paths
    src = p{1}{1}(1);
    dst = p{1}{1}(end);
    T_any(i, 1) = src;
    T_any(i, 2) = dst;
    T_any(i, 3) = T_anyAux(i, 2);
    T_any(i, 4) = T_anyAux(i, 3);
    i = i + 1;
end

k = 6;
sP= cell(1,length(T_uni));
nSP= zeros(1,length(T_uni));
for f=1:length(T_uni)
    [shortestPath, totalCost] = kShortestPath(L,T_uni(f,1),T_uni(f,2),k);
    sP{f}= shortestPath;
    nSP(f)= length(totalCost);
end

T = [T_uni; T_any];
sP= horzcat(sP, paths);
nSP= [nSP ones(1, length(T_any))];
anyFlows = [length(T)-length(T_any)+1:length(T)];

t= tic;
timeLimit= 60;
best_energy= inf;
contador= 0;
somador= 0;
% Links_C= zeros(1,length(Links));
while toc(t) < timeLimit
    % greedy randomized start
    [sol, load, Loads, energy, Links_C] = GreedyRandomizedStrategy_Energy_Ex3(nNodes, Links, T, sP, nSP, L, anyFlows);
    
    while energy == inf
        [sol, load, Loads, energy, Links_C] = GreedyRandomizedStrategy_Energy_Ex3(nNodes, Links, T, sP, nSP, L, anyFlows);
    end
    
    [sol, load, Loads, energy, Links_C] = HillClimbingStrategy_Energy_Ex3(nNodes, Links, T, sP, nSP, sol, load, Loads, energy, L, Links_C);
    
    if energy < best_energy 
        best_sol= sol;
        best_load= load;
        best_Loads= Loads;
        best_energy= energy;
        bestTime = toc(t);
        best_Links_C = Links_C;
    end
    contador= contador + 1;
    somador= somador + energy;
end

fprintf('k-shortest paths: %d\n', k);
fprintf('No. sol = %d, Av. E = %.2f\n', contador, somador/contador);
fprintf('Worst link load = %.1f Gbps\n', best_load);
fprintf('Total energy consumption: %.2f\n', best_energy);

sleeping_nodes = '';
for i = 1 : length(best_Loads)
    if max(best_Loads(i, 3:4)) == 0
        sleeping_nodes = append(sleeping_nodes, ' {', num2str(best_Loads(i,1)), ',', num2str(best_Loads(i,2)), '}');
    end
end

fprintf('List of links in sleeping mode:%s\n', sleeping_nodes);

updated_nodes = '';
for i = 1 : length(best_Links_C)
   if(best_Links_C(i) == 1)
        updated_nodes = append(updated_nodes, ' {', num2str(best_Loads(i,1)), ',', num2str(best_Loads(i,2)), '}');
   end
end

fprintf('List of links updated to a capacity of 100 Gbps:%s\n', updated_nodes);
fprintf('Time to obtain the best solution: %.2f sec\n', bestTime);
