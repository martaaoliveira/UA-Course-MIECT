clear all
close all
clc

load('InputDataProject2.mat')

% ex 3c
k = 6;
sP= cell(1,length(T_uni));
nSP= zeros(1,length(T_uni));
for f=1:length(T_uni)
    [shortestPath, totalCost] = kShortestPath(L,T_uni(f,1),T_uni(f,2),k);
    sP{f}= shortestPath;
    nSP(f)= length(totalCost);
end

nNodes= size(Nodes,1);
possible_any_Nodes = [4 5 6 12 13];
combs_any_nodes = nchoosek(possible_any_Nodes, 2);      % all combinations of anycastNodes
bestGlobalEnergy= inf;
T_any_Original= T_any;

for c = 1 : length(combs_any_nodes)
    anycast_Nodes = combs_any_nodes(c, :);
    source_Nodes = setdiff(T_any_Original(:,1)', anycast_Nodes);
%     source_Nodes = T_any(:,1)';
   
    [~, paths] = bestCostPaths(L, source_Nodes, anycast_Nodes);

    T_anyAux = T_any_Original;
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

    T = [T_uni; T_any];
    sP= horzcat(sP, paths);
    nSP= [nSP ones(1, length(T_any))];
    anyFlows = [length(T)-length(T_any)+1:length(T)];
   
    t= tic;
    timeLimit= 60;
    bestLocalEnergy= inf;
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

        if energy < bestLocalEnergy 
            aux_best_sol= sol;
            aux_best_load= load;
            aux_best_Loads= Loads;
            aux_bestTime = toc(t);
            aux_best_Links_C = Links_C;
            bestLocalEnergy = energy;
        end
        contador= contador + 1;
        somador= somador + load;
    end
    
    if bestLocalEnergy < bestGlobalEnergy 
        best_Anycast_Nodes = anycast_Nodes;
        best_sol= aux_best_sol;
        best_load= aux_best_load;
        best_Loads= aux_best_Loads;
        bestTime = aux_bestTime;
        best_Links_C = aux_best_Links_C;
        bestGlobalEnergy = bestLocalEnergy;
    end
end

% fprintf('W = %.2f Gbps, No. sol = %d, Av. W = %.2f\n', best_load, contador, somador/contador);
fprintf('k-shortest paths: %d\n', k);
fprintf('Best Anycast Nodes: %s\n', num2str(best_Anycast_Nodes));
fprintf('Worst link load = %.1f Gbps\n', best_load);
fprintf('Total energy consumption: %.2f\n', bestGlobalEnergy);

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
