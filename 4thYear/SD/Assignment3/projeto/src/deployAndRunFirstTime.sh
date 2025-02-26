xterm  -T "RMI registry" -hold -e "./RMIRegistryDeployAndRun.sh" &
sleep 10
xterm  -T "Registry" -hold -e "./RegistryDeployAndRun.sh" &
sleep 4
xterm  -T "General Repository" -hold -e "./GeneralReposDeployAndRun.sh" &
sleep 2
xterm  -T "Playground" -hold -e "./PlaygroundRunAndDeploy.sh" &
sleep 2
xterm  -T "RefereeSite" -hold -e "./refereeSiteRunAndDeploy.sh" &
sleep 2
xterm  -T "Bench" -hold -e "./BenchRunAndDeploy.sh" &
sleep 5
xterm  -T "Contestants" -hold -e "./ContestantRunAndDeploy.sh" &
sleep 2
xterm  -T "Coach" -hold -e "./CoachRunAndDeploy.sh" &
sleep  2
xterm  -T "Referee" -hold -e "./refereeRunAndDeploy.sh" &