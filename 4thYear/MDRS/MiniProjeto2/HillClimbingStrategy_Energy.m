function [sol, load, Loads, energy] = HillClimbingStrategy_Energy(nNodes, Links, T, sP, nSP, sol, load, Loads, energy, L, C)
    nFlows = size(T,1);    
    % set the best local variables
    bestLocalLoad = load;
    bestLocalSol = sol;
    bestLocalLoads = Loads;
    bestLocalEnergy = energy;
    
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
                    % calculate loads and energy
                    [aux_load, aux_Loads, aux_energy] = calculateLinkLoads_Energy(nNodes, Links, T, sP, aux_sol, L, C);
                    
                    % check if the current energy is better than bestLocalEnergy
                    if aux_energy < bestLocalEnergy
                        bestLocalLoad = aux_load;
                        bestLocalLoads = aux_Loads;
                        bestLocalEnergy = aux_energy;
                        bestLocalSol = aux_sol;
                    end
                end
            end
        end

        if bestLocalEnergy < energy
            load = bestLocalLoad;
            Loads = bestLocalLoads;
            sol = bestLocalSol;
            energy = bestLocalEnergy;
        else
            improved = false;
        end
    end
end