function [sol, eN] = greedyRandomizedEnergy(nNodes, Links, T, sP, nSP,alfa)
t= tic;
nLinks= size(Links,1);
randFlows = randperm(nFlows); % array numa ordem aleatória
sol= zeros(1,nFlows);

for f= randFlows
    energy=0;
    bestLoad=inf;
    k_best = 0;
    best = inf;
    for k= 1 : nSP(flow)
        sol(f)= k;
        Loads= calculateLinkLoads(nNodes,Links,T,sP,sol);
        load= max(max(Loads(:,3:4)));
        if load<=10*alfa
            energy = 0;
            for a=1:nLinks
                if Loads(a,3)+Loads(a,4)>0
                    energy = energy + 10 + 0.1* (L(Loads(a,1),Loads(a,2))); %a energia e igual a energia + o comprimento do link (L -> comprimento do link)
                else
                    energy=enegery + 0.5;
                end
            end
        else
            energy=inf;
        end
        if energy < bestLoad
            k_best= k;
            best= energy;
        end
    end
    if k_best>0
        sol(f) = k_best;
    else
        break;
    end
end
eN= best;

end