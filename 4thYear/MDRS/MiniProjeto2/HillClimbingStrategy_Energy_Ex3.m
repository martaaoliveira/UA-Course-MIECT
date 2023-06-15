function [sol, load, Loads, energy, Links_C] = HillClimbingStrategy_Energy_Ex3(nNodes, Links, T, sP, nSP, sol, load, Loads, energy, L, Links_C)
    nFlows = size(T,1);    
    % set the best local variables
    bestLocalLoad = load;
    bestLocalSol = sol;
    bestLocalLoads = Loads;
    bestLocalEnergy = energy;
    bestLocalLinks_C = Links_C;
    
    % Hill Climbing Strategy
    improved = true;
    while improved
        % test each path of the flow
        for flow = 1 : nFlows
            % test each path of the flow
            for path = 1 : nSP(flow)
                if path ~= sol(flow)
                    % change the path for that flow
                    aux_sol = sol;
                    aux_sol(flow) = path;
%                     aux_Links_C = zeros(1, length(Links));
                    % calculate loads and energy
                    [aux_load, aux_Loads, aux_energy, aux_Links_C] = calculateLinkLoads_Energy_Ex3(nNodes, Links, T, sP, aux_sol, L);
                    
                    % check if the current energy is better than bestLocalEnergy
                    if aux_energy < bestLocalEnergy
                        bestLocalLoad = aux_load;
                        bestLocalLoads = aux_Loads;
                        bestLocalEnergy = aux_energy;
                        bestLocalSol = aux_sol;
                        bestLocalLinks_C = aux_Links_C;
                    end
                    
%                     for idx = 1 : length(Links)
%                         aux_Links_C(idx) = 1;
%                         [aux_load, aux_Loads, aux_energy] = calculateLinkLoads_Energy_Ex3(nNodes, Links, T, sP, aux_sol, L, aux_Links_C);
% 
%                         % check if the current energy is better than best_energy
%                         if aux_energy < bestLocalEnergy
%                             bestLocalLoad = aux_load;
%                             bestLocalLoads = aux_Loads;
%                             bestLocalEnergy = aux_energy;
%                             bestLocalSol = aux_sol;
%                             bestLocalLinks_C = aux_Links_C;
%                         else
%                             aux_Links_C(idx) = 0;
%                         end
%                     end
                end
            end
        end

        if bestLocalEnergy < energy
            load = bestLocalLoad;
            Loads = bestLocalLoads;
            sol = bestLocalSol;
            energy = bestLocalEnergy;
            Links_C = bestLocalLinks_C;
        else
            improved = false;
        end
    end
end