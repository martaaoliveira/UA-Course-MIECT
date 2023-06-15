function [load, Loads, energy]= calculateLinkLoads_Energy(nNodes,Links,T,sP,Solution, L, C)
    nFlows= size(T,1);
    nLinks= size(Links,1);
    aux= zeros(nNodes);
    energy= 0;
    totalLoads_nodes = zeros(1, nNodes);
    for i= 1:nFlows
        if Solution(i)>0
            path= sP{i}{Solution(i)};
            for j=2:length(path)
                aux(path(j-1),path(j))= aux(path(j-1),path(j)) + T(i,3); 
                aux(path(j),path(j-1))= aux(path(j),path(j-1)) + T(i,4);
            end
            % CALCULATE THE ENERGY CONSUMPTION OF THE NODES
%             flow_extremes = [path(1), path(end)];
%             for j = 1 : length(T)
%                 if isequal(flow_extremes, T(j, 1:2))
%                     for n = path
%                         totalLoads_nodes(n) = totalLoads_nodes(n) + T(j,3) + T(j,4);
%                     end
%                     break;
%                 end
%             end
            for n = path
                totalLoads_nodes(n) = totalLoads_nodes(n) + T(i,3) + T(i,4);
            end
        end
    end
    Loads= [Links zeros(nLinks,2)];
    for i= 1:nLinks
        Loads(i,3)= aux(Loads(i,1),Loads(i,2));
        Loads(i,4)= aux(Loads(i,2),Loads(i,1));
    end
    load = max(max(Loads(:,3:4)));
    % If the worst link load is greater than max capacity , energy will be infinite    
    if load > C
       energy = inf; 
    else
        for i= 1:nLinks
            % link in sleeping mode
            if max(Loads(i, 3:4)) == 0
                energy = energy + 2;
            else
                % CALCULATE THE ENERGY CONSUMPTION OF THE LINKS
                l = L(Loads(i, 1), Loads(i, 2)); % l = length from nodeA to nodeB
                % Capacity of the link can be 50 or 100 (values the of energy consumption will be different)
                %if C == 50
                    energy = energy + 6 + 0.2 * l;
                %else
                %    energy = energy + 8 + 0.3 * l;
                %end
            end
        end

        for i = 1 : length(totalLoads_nodes)
            energy = energy + 10 + 90 * (totalLoads_nodes(i) / 500)^2; 
        end
    end
end