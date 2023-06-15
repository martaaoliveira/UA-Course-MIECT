clear all
close all
clc

load('InputData.mat')
nNodes= size(Nodes,1);
nLinks= size(Links,1);
nFlows= size(T,1);

k=4;
f=1;
[shortestPath, totalCost] = kShortestPath(L,T(f,1),T(f,2),k);
for i=1:k
    fprintf('Path %d = %s (length = %d)\n',i,num2str(shortestPath{i}),totalCost(i));
end


