function [load, Loads, energy, Links_C]= calculateLinkLoads_Energy_Ex3(nNodes,Links,T,sP,Solution, L)
    nFlows= size(T,1);
    nLinks= size(Links,1);
    aux= zeros(nNodes);
    energy= 0;
    totalLoads_nodes = zeros(1, nNodes);
    Links_C = zeros(1,nLinks);
    for i= 1:nFlows
        if Solution(i)>0
            path= sP{i}{Solution(i)};
            for j=2:length(path)
                aux(path(j-1),path(j))= aux(path(j-1),path(j)) + T(i,3); 
                aux(path(j),path(j-1))= aux(path(j),path(j-1)) + T(i,4);
            end
            % CALCULATE TOTAL TRAFFIC LOAD SUPPORTED BY THE NODES
            for n = path
                totalLoads_nodes(n) = totalLoads_nodes(n) + T(i,3) + T(i,4);
            end
        end
    end
    % CALCULATE THE ENERGY CONSUMPTION OF THE NODES
    for i = 1 : length(totalLoads_nodes)
        energy = energy + 10 + 90 * (totalLoads_nodes(i) / 500)^2; 
    end
    
    Loads= [Links zeros(nLinks,2)];
    for i= 1:nLinks
        Loads(i,3)= aux(Loads(i,1),Loads(i,2));
        Loads(i,4)= aux(Loads(i,2),Loads(i,1));
    end
    load = max(max(Loads(:,3:4)));
    % If the worst link load is greater than max capacity , energy will be infinite    
    if load > 100
       energy = inf; 
    elseif load < 50
        for i= 1:nLinks
            % link in sleeping mode
            if max(Loads(i, 3:4)) == 0
                energy = energy + 2;
            else
                % CALCULATE THE ENERGY CONSUMPTION OF THE LINKS
                l = L(Loads(i, 1), Loads(i, 2)); % l = length from nodeA to nodeB
                energy = energy + 6 + 0.2 * l;
            end
        end
    else
        for i= 1:nLinks
            C = 50;
            % link in sleeping mode
            maxLoad = max(Loads(i, 3:4));
            if  maxLoad == 0
                energy = energy + 2;
            else
                % CALCULATE THE ENERGY CONSUMPTION OF THE LINKS
                l = L(Loads(i, 1), Loads(i, 2)); % l = length from nodeA to nodeB
                if  maxLoad > 50  % The max load cannot be higher than the max capacity
                    Links_C(i) = 1;
                    C = 100;
                end

                if C == 50
                    energy = energy + 6 + 0.2 * l;
                else
                    energy = energy + 8 + 0.3 * l;
                end
            end
        end
    end
end