load('InputData2.mat')
nNodes= size(Nodes,1);
nLinks= size(Links,1);
nFlows= size(T,1);



v=2 * 10^5;
D=L/v;

down_l=[0.7 1.5 2.1 1.0 1.3 2.7 2.2 0.8 1.7 1.9 2.8];
up_l=[0.1 0.2 0.3 0.1 0.1 0.4 0.3 0.1 0.2 0.3 0.4];



for i=1:11

[path1,cost1]=kShortestPath(L,i,3);
[path2,cost2]=kShortestPath(L,i,5,);
end










% Computing up to k=inf shortest paths for all flows from 1 to nFlows:
k= inf;
sP= cell(1,nFlows);
nSP= zeros(1,nFlows);
for f=1:nFlows
    [shortestPath, totalCost] = kShortestPath(L,T(f,1),T(f,2),k);
    sP{f}= shortestPath;
    nSP(f)= length(totalCost);
end
% sP{f}{i} is the i-th path of flow f
% nSP(f) is the number of paths of flow f

% Compute the link loads using the first (shortest) path of each flow:
sol= ones(1,nFlows);
Loads= calculateLinkLoads(nNodes,Links,T,sP,sol);
% Determine the worst link load:
maxLoad= max(max(Loads(:,3:4)));

% print the minimum no. of paths
fprintf('Minimum no. of paths= %d\n', min(nSP));
for flow = find(nSP==min(nSP))
    fprintf('\tFlow %d (%d -> %d)\n', flow, sP{flow}{sol(flow)}(1), sP{flow}{sol(flow)}(end));
end
