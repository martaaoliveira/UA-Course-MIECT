clear all
close all
clc

load('InputData2.mat')
nNodes= size(Nodes,1);
nLinks= size(Links,1);
nFlows= size(T,1);

MTBF= (450*365*24)./L;
A= MTBF./(MTBF + 24);
A(isnan(A))= 0;
A= A + eye(size(A));
Alog= -log(A);

% Computing up to k=6 pairs of link disjoint paths
% for all flows from 1 to nFlows:
k= 1;
sP= cell(2,nFlows);
nSP= zeros(1,nFlows);
% sP{1,f}{i} is the 1st path of the i-th path pair of flow f
% sP{2,f}{i} is the 2nd path of the i-th path pair of flow f
% nSP(f) is the number of path pairs of flow f
a = ones(1,nFlows);

for f=1:nFlows
    [shortestPath, totalCost] = kShortestPath(Alog,T(f,1),T(f,2),k);
    sP{1,f}= shortestPath;
    nSP(f)= length(totalCost);
    path=sP{1,f}{1};
    for i=1:length(path) - 1
        a(f) =  a(f) * A(path(i),path(i+1));
    end
    %fprintf("Flow %d: Availability= %.7f - Path= %s\n", f, a(f), num2str(sP{1,f}{1}));
end

AvgAvlb = sum(a) / nFlows;

fprintf("Average availability= %.7f\n", AvgAvlb);