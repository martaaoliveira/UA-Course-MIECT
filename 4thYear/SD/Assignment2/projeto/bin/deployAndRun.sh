xterm  -T "General Repository" -hold -e "./GeneralReposDeployAndRun.sh" &
sleep 1
xterm  -T "RefereeSite" -hold -e "./refereeSiteRunAndDeploy.sh" &
sleep 1
xterm  -T "Playground" -hold -e "./PlaygroundRunAndDeploy.sh" &
sleep 1
xterm  -T "Bench" -hold -e "./BenchRunAndDeploy.sh" &
sleep 5
xterm  -T "Contestant" -hold -e "./ContestantRunAndDeploy.sh" &
sleep 1
xterm  -T "Coach" -hold -e "./CoachRunAndDeploy.sh" &
sleep 1
xterm  -T "Referee" -hold -e "./refereeRunAndDeploy.sh" &
