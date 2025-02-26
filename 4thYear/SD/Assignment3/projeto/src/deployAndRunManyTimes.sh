for i in $(seq 1 100)
do 
    xterm  -T "General Repository" -hold -e "./GeneralReposDeployAndRun.sh" &
    pid1=$!  # Armazena o PID do último processo em background
    sleep 1

    xterm  -T "RefereeSite" -hold -e "./refereeSiteRunAndDeploy.sh" &
    pid2=$!
    sleep 1

    xterm  -T "Playground" -hold -e "./PlaygroundRunAndDeploy.sh" &
    pid3=$!
    sleep 1

    xterm  -T "Bench" -hold -e "./BenchRunAndDeploy.sh" &
    pid4=$!
    sleep 5

    xterm  -T "Contestant" -hold -e "./ContestantRunAndDeploy.sh" &
    pid5=$!
    sleep 1

    xterm  -T "Coach" -hold -e "./CoachRunAndDeploy.sh" &
    pid6=$!
    sleep 1

    xterm  -T "Referee" -hold -e "./refereeRunAndDeploy.sh" &
    pid7=$!

    # Aguarda 2 minutos antes de matar todos os xterms iniciados neste ciclo
    sleep 50

    # Mata os processos usando os PIDs armazenados
    kill $pid1 $pid2 $pid3 $pid4 $pid5 $pid6 $pid7

    sleep 2
done
