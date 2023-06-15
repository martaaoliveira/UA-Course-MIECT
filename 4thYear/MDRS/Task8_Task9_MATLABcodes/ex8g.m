clear all
close all
clc

load('InputData2.mat')
nNodes= size(Nodes,1);
nLinks= size(Links,1);
nFlows= size(T,1);

k= inf;
sP= cell(1,nFlows);
nSP= zeros(1,nFlows);
for f=1:nFlows
    [shortestPath, totalCost] = kShortestPath(L,T(f,1),T(f,2),k);
    sP{f}= shortestPath;
    nSP(f)= length(totalCost);
end

%Optimization algorithm based on random strategy:
t= tic;
timeLimit= 5;
bestLoad= inf;
contador= 0;
somador= 0;
while toc(t) < timeLimit
    [sol,Loads,load] = GreedyRandomized(nNodes,Links,T,sP,nSP);
    if load<bestLoad
        bestTime = toc(t);
        bestSol= sol;
        bestLoad= load;
        bestLoads= Loads;
    end
    contador= contador+1;
    somador= somador+load;
end
%Output of routing solution:
fprintf('\nRouting paths of the solution:\n')
for f= 1:nFlows
    selectedPath= bestSol(f);
    fprintf('Flow %d - Path %d:  %s\n',f,selectedPath,num2str(sP{f}{selectedPath}));
end
%Output of link loads of the routing solution:
fprintf('Worst link load of the best solution = %.2f\n',bestLoad);
fprintf('Link loads of the best solution:\n')
for i= 1:nLinks
    fprintf('{%d-%d}:\t%.2f\t%.2f\n',bestLoads(i,1),bestLoads(i,2),bestLoads(i,3),bestLoads(i,4))
end
%Output of performace values:
fprintf('No. of generated solutions = %d\n',contador);
fprintf('Avg. worst link load among all solutions= %.2f\n',somador/contador);
fprintf('Time=%.2f\n',bestTime);

function [sol,Loads,load] = GreedyRandomized(nNodes,Links,T,sP,nSP)
    nFlows = size(T,1); % number of flows
    sol= zeros(1,nFlows);
    randFlows = randperm(nFlows); % chooses a random order of flows 
    
    % iterate through all flows (in a random order)
    for f = randFlows
        bestLoad = inf;
        %iterate through all path's of the flow
        for path = 1 : nSP(f)
            sol(f) = path; 
            % calculate loads
            Loads = calculateLinkLoads(nNodes, Links, T, sP, sol);
            load = max(max(Loads(:, 3:4)));
            
            % check if the current load is better then bestLoad
            if load < bestLoad
                pathBest = path;    % update the path of the sol
                bestLoad = load;  % update the value of bestLoad
            end
        end
        sol(f) = pathBest;
    end
    load = bestLoad;
    Loads = calculateLinkLoads(nNodes, Links, T, sP, sol);
end