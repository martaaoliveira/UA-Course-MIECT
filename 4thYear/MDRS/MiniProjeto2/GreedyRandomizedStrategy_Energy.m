function [sol, load, Loads, energy] = GreedyRandomizedStrategy_Energy(nNodes,Links,T,sP,nSP,L,C, anyFlows)
    nFlows = size(T,1); % number of flows
    sol= zeros(1,nFlows);
%     randFlows = randperm(nFlows); % chooses a random order of flows 
    randFlows = [anyFlows, randperm(nFlows -9)]; % chooses a random order of flows for the unicast flows only
    
    % iterate through all flows (in a random order)
    for f = randFlows
        path_index = 0;
        best_load = inf;
        best_Loads = inf;
        best_energy = inf;
        %iterate through all path's of the flow
        for path = 1 : nSP(f)
            sol(f) = path; 
            % calculate loads and energy
            [load, Loads, energy] = calculateLinkLoads_Energy(nNodes, Links, T, sP, sol, L, C);
            
            % check if the current energy is better than best_energy
            if energy < best_energy 
                path_index = path; % update the path of the sol
                best_load = load;  % update the value of bestLoad
                best_Loads = Loads;
                best_energy = energy;
            end
        end
        if path_index == 0
            break;
        else
           sol(f) = path_index; 
        end
    end
    
    load = best_load;
    Loads = best_Loads;
    energy = best_energy;
end