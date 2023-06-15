clear all
close all
clc

load('InputDataProject2.mat')

% ex 1.d)
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

k = 2;
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
timeLimit= 30;
bestLoad= inf;
contador= 0;
somador= 0;
while toc(t) < timeLimit
    % greedy randomized start
    [sol, Loads, load] = GreedyRandomizedStrategy(nNodes, Links, T, sP, nSP, anyFlows);

    [sol, load] = HillClimbingStrategy(nNodes, Links, T, sP, nSP, sol, load);

    if load<bestLoad
        bestSol= sol;
        bestLoad= load;
        bestLoads= Loads;
        bestLoadTime = toc(t);
    end
    contador= contador + 1;
    somador= somador + load;
end

fprintf('k-shortest paths: %d\n', k);
fprintf('No. sol = %d, Av. W = %.2f\n', contador, somador/contador);
fprintf('Worst link load = %.1f Gbps\n', bestLoad);
% Calculate energy consumption of each node (E_n)
Loads = bestLoads;
totalLoads_nodes = zeros(1, nNodes);
energy_nodes = zeros(1, nNodes);

idx = 1;
for p = sP
    path = p{1}{bestSol(idx)};
    f_extremes = [path(1), path(end)];
    for i = 1 : length(T)
        if isequal(f_extremes, T(i, 1:2))
            for n = path
                totalLoads_nodes(n) = totalLoads_nodes(n) + T(i,3) + T(i,4);
            end
            break;
        end
    end
    idx = idx + 1;
end

for i = 1 : length(totalLoads_nodes)
   energy_nodes(i) = 10 + 90 * (totalLoads_nodes(i) / 500)^2; 
end

% Calculate energy consumption of each link (E_l)
sleeping_nodes = '';
energy_links = zeros(1, length(Loads));
for i = 1 : length(Loads)
    if max(Loads(i, 3:4)) == 0
        sleeping_nodes = append(sleeping_nodes, ' {', num2str(Loads(i,1)), ',', num2str(Loads(i,2)), '}');
        energy_links(i) = 2;
    else
        energy_links(i) = 6 + 0.2 * L(Loads(i,1), Loads(i,2));
    end
end

total_energy = sum(energy_nodes) + sum(energy_links);

fprintf('Total energy consumption: %.2f\n', total_energy);
fprintf('List of links in sleeping mode:%s\n', sleeping_nodes);
fprintf('Time to obtain the best solution: %.2f sec\n', bestLoadTime);