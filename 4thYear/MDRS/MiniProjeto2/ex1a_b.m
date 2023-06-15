clear all
close all
clc

load('InputDataProject2.mat')

% ex 1.a)
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

sP= cell(1,length(T_uni));
for f=1:length(T_uni)
    [shortestPath, totalCost] = kShortestPath(L,T_uni(f,1),T_uni(f,2),1);
    sP{f}= shortestPath;
end

sP= horzcat(sP, paths);
T = [T_uni; T_any];

sol= ones(1,length(T));
Loads= calculateLinkLoads(nNodes,Links,T,sP,sol);
% Determine the worst link load:
maxLoad= max(max(Loads(:,3:4)));

fprintf("Ex. 1.a)\n");
fprintf("Anycast Nodes = %s\n", num2str(anycast_Nodes));
fprintf('Worst link load = %.1f Gbps\n', maxLoad);
for i = 1 : length(Loads)
    fprintf('{%d - %d}:\t%.2f\t%.2f\n', Loads(i), Loads(i+length(Loads)), Loads(i+length(Loads)*2), Loads(i+length(Loads)*3))
end

% ex 1.b)
% Calculate energy consumption of each node (E_n)
totalLoads_nodes = zeros(1, nNodes);
energy_nodes = zeros(1, nNodes);

for p = sP
    path = p{1}{1};
    flow_extremes = [path(1), path(end)];
    for i = 1 : length(T)
        if isequal(flow_extremes, T(i, 1:2))
            for n = path
                totalLoads_nodes(n) = totalLoads_nodes(n) + T(i,3) + T(i,4);
            end
            break;
        end
    end
end

for i = 1 : length(totalLoads_nodes)
   energy_nodes(i) = 10 + 90 * (totalLoads_nodes(i) / 500)^2; 
end

% Calculate energy consumption of each link (E_l)
sleeping_mode_links = '';
energy_links = zeros(1, length(Loads));
for i = 1 : length(Loads)
    if max(Loads(i, 3:4)) == 0
        sleeping_mode_links = append(sleeping_mode_links, ' {', num2str(Loads(i,1)), ',', num2str(Loads(i,2)), '}');
        energy_links(i) = 2;
    else
        energy_links(i) = 6 + 0.2 * L(Loads(i,1), Loads(i,2));
    end
end

total_energy = sum(energy_nodes) + sum(energy_links);

fprintf('\nEx. 1.b)\n');
fprintf('Total energy consumption: %.2f\n', total_energy);
fprintf('List of links in sleeping mode:%s\n', sleeping_mode_links);
