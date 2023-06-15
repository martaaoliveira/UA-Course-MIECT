function [sol,Loads,load] = GreedyRandomizedStrategy(nNodes,Links,T,sP,nSP, anyFlows)
    nFlows = size(T,1); % number of flows
    sol= zeros(1,nFlows);
    randFlows = [anyFlows, randperm(nFlows -9)]; % chooses a random order of flows for the unicast flows only
%     randFlows = randperm(nFlows); % chooses a random order of flows 
    
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