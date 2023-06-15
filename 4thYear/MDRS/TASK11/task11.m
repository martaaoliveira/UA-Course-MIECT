clear all
close all
clc

load('InputData2.mat')
nNodes= size(Nodes,1);
nLinks= size(Links,1);
nFlows= size(T,1);


% Computing up to k=inf shortest paths for all flows from 1 to nFlows:
k= inf;
sP= cell(1,nFlows);
nSP= zeros(1,nFlows);
for f=1:nFlows
    [shortestPath, totalCost] = kShortestPath(L,T(f,1),T(f,2),k);
    sP{f}= shortestPath; % sP{f}{i} is the i-th path of flow f
    nSP(f)= length(totalCost); % nSP(f) is the number of paths of flow f
end


%%%%%%%%%%%%%%%%% Multi start hill climbing with random %%%%%%%%%%%%%%%%%%
t= tic;
timeLimit= 5;
bestLoad= inf;
contador= 0;
somador= 0;
while toc(t) < timeLimit
    % randomized start
    sol = zeros(1,nFlows);
    for f= 1:nFlows
        sol(f)= randi(nSP(f));
    end
    
    Loads = calculateLinkLoads(nNodes, Links, T, sP, sol);
    load = max(max(Loads(:, 3:4)));

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

fprintf('Multi start hill climbing with random (all possible paths):\n');
fprintf('\t W = %.2f Gbps, No. sol = %d, Av. W = %.2f, time = %.2f sec\n', bestLoad, contador, somador/contador, bestLoadTime);