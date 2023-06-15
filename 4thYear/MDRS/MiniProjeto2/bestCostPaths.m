function [costs, paths] = bestCostPaths(L, source_Nodes, anycast_Nodes)
    paths = cell(1, length(source_Nodes));
    costs = zeros(1, length(source_Nodes));

    for i = 1:length(source_Nodes)
        best_cost = inf;
        
        for n = 1:length(anycast_Nodes)
            [path, cost] = kShortestPath(L, source_Nodes(i), anycast_Nodes(n), 1);
            
            if cost < best_cost
                paths{i} = path;
                best_cost = cost;
                costs(i) = cost;
            end
        end
    end

end