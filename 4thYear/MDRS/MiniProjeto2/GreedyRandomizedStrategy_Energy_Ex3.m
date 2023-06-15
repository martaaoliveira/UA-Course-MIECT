function [sol, load, Loads, energy, Links_C] = GreedyRandomizedStrategy_Energy_Ex3(nNodes,Links,T,sP,nSP,L,anyFlows)
    nFlows = size(T,1); % number of flows
    sol= zeros(1,nFlows);
%     randFlows = randperm(nFlows); % chooses a random order of flows 
    randFlows = [anyFlows, randperm(nFlows -9)]; % chooses a random order of flows for the unicast flows only
    best_Links_C = zeros(1, length(Links));
    
    % iterate through all flows (in a random order)
    for f = randFlows
        path_index = 0;
        best_load = inf;
        best_Loads = inf;
        best_energy = inf;
        
        %iterate through all path's of the flow
        for path = 1 : nSP(f)
            sol(f) = path; 
%             Links_C = zeros(1, length(Links));
            
            % calculate loads and energy
            [load, Loads, energy, Links_C] = calculateLinkLoads_Energy_Ex3(nNodes, Links, T, sP, sol, L);

            % check if the current energy is better than best_energy
            if energy < best_energy 
                path_index = path; % update the path of the sol
                best_load = load;  % update the value of bestLoad
                best_Loads = Loads;
                best_energy = energy;
                best_Links_C = Links_C;
            end
            
%             for idx = 1 : length(Links)
%                 Links_C(idx) = 1;
%                 [load, Loads, energy] = calculateLinkLoads_Energy_Ex3(nNodes, Links, T, sP, sol, L, Links_C);
% 
%                 % check if the current energy is better than best_energy
%                 if energy < best_energy 
%                     path_index = path; % update the path of the sol
%                     best_load = load;  % update the value of bestLoad
%                     best_Loads = Loads;
%                     best_energy = energy;
%                     best_Links_C = Links_C;
%                 else
%                     Links_C(idx) = 0;
%                 end
%             end
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
    Links_C = best_Links_C;
end