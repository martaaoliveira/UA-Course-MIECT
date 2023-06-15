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

for f=1:nFlows
    [shortestPath, totalCost] = kShortestPath(Alog,T(f,1),T(f,2),k);
    sP{1,f}= shortestPath;
    nSP(f)= length(totalCost);
    
    for i= 1:nSP(f)
        Aaux= Alog;
        path1= sP{1,f}{i};
        for j=2:length(path1)
            Aaux(path1(j),path1(j-1))= inf;
            Aaux(path1(j-1),path1(j))= inf;
        end
        [shortestPath, totalCost] = kShortestPath(Aaux,T(f,1),T(f,2),1);
        if ~isempty(shortestPath)
            sP{2,f}{i}= shortestPath{1};
        end
    end
    a1 = 1;
    a2 = 1;
    path1=sP{1,f}{1};
    
    for i=1:length(path1) - 1
        a1 =  a1 * A(path1(i),path1(i+1));
    end
    if ~isempty(sP{2,f})
        path2=sP{2,f}{1};
        for i=1:length(path2) - 1
            a2 =  a2 * A(path2(i),path2(i+1));
        end
    else
        a2=0;
    end
    a = 1 - ((1-a1) * (1-a2)); % availabiity of disjunt paths
    if ~isempty(sP{2,f})
        fprintf("Flow %2d: Availability= %.7f - Path= %s\n\t\t\t\t\t\t\t\t   Path= %s\n", f, a, num2str(sP{1,f}{1}),num2str(sP{2,f}{1}));
    else
        fprintf("Flow %2d: Availability= %.7f - Path= %s\n\t\t\t\t\t\t\t\t   Path= \n", f, a, num2str(sP{1,f}{1}));
    end
end