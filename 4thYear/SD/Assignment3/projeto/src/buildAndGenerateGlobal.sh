echo "Compiling source code."
javac -cp ".:./genclass.jar" */*.java */*/*.java
echo "Distributing intermediate code to the different execution environments."

echo "  RMI registry"
rm -rf dirRMIRegistry/interfaces
mkdir -p dirRMIRegistry/interfaces
cp interfaces/*.class dirRMIRegistry/interfaces

echo "  Register Remote Objects"
rm -rf dirRegistry/serverSide dirRegistry/interfaces
mkdir -p dirRegistry/serverSide dirRegistry/serverSide/main dirRegistry/serverSide/objects dirRegistry/interfaces
cp serverSide/main/ServerRegisterRemoteObject.class dirRegistry/serverSide/main
cp serverSide/objects/RegisterRemoteObject.class dirRegistry/serverSide/objects
cp interfaces/Register.class dirRegistry/interfaces


echo "  General Repository of Information"
rm -rf dirGeneralRepo/serverSide dirGeneralRepo/clientSide dirGeneralRepo/interfaces
mkdir -p dirGeneralRepo/serverSide dirGeneralRepo/serverSide/main dirGeneralRepo/serverSide/objects dirGeneralRepo/interfaces \
         dirGeneralRepo/clientSide dirGeneralRepo/clientSide/entities
cp serverSide/main/SimulPar.class serverSide/main/ServerGeneralRepo.class dirGeneralRepo/serverSide/main
cp serverSide/objects/GeneralRepo.class dirGeneralRepo/serverSide/objects
cp interfaces/Register.class interfaces/GeneralRepoInterface.class dirGeneralRepo/interfaces
cp clientSide/entities/ContestantState.class clientSide/entities/RefereeState.class clientSide/entities/CoachState.class dirGeneralRepo/clientSide/entities

echo "  Bench"
rm -rf dirBench/serverSide dirBench/clientSide dirBench/interfaces dirBench/commInfra
mkdir -p dirBench/serverSide dirBench/serverSide/main dirBench/serverSide/objects dirBench/interfaces \
         dirBench/clientSide dirBench/clientSide/entities dirBench/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerBench.class dirBench/serverSide/main
cp serverSide/objects/Bench.class dirBench/serverSide/objects
cp interfaces/*.class dirBench/interfaces
cp clientSide/entities/ContestantState.class clientSide/entities/RefereeState.class clientSide/entities/CoachState.class dirBench/clientSide/entities
cp commInfra/*.class dirBench/commInfra

echo "  PlayGround"
rm -rf dirPlayground/serverSide dirPlayground/clientSide dirPlayground/interfaces dirPlayground/commInfra
mkdir -p dirPlayground/serverSide dirPlayground/serverSide/main dirPlayground/serverSide/objects dirPlayground/interfaces \
         dirPlayground/clientSide dirPlayground/clientSide/entities dirPlayground/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerPlayground.class dirPlayground/serverSide/main
cp serverSide/objects/Playground.class dirPlayground/serverSide/objects
cp interfaces/*.class dirPlayground/interfaces
cp clientSide/entities/ContestantState.class clientSide/entities/RefereeState.class clientSide/entities/CoachState.class dirPlayground/clientSide/entities
cp commInfra/*.class dirPlayground/commInfra

echo "  RefereeSite"
rm -rf dirRefereeSite/serverSide dirRefereeSite/clientSide dirRefereeSite/interfaces dirRefereeSite/commInfra
mkdir -p dirRefereeSite/serverSide dirRefereeSite/serverSide/main dirRefereeSite/serverSide/objects dirRefereeSite/interfaces \
         dirRefereeSite/clientSide dirRefereeSite/clientSide/entities dirRefereeSite/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerRefereeSite.class dirRefereeSite/serverSide/main
cp serverSide/objects/RefereeSite.class dirRefereeSite/serverSide/objects
cp interfaces/*.class dirRefereeSite/interfaces
cp clientSide/entities/ContestantState.class clientSide/entities/RefereeState.class clientSide/entities/CoachState.class dirRefereeSite/clientSide/entities
cp commInfra/*.class dirRefereeSite/commInfra

echo "  Contestants"
rm -rf dirContestant/serverSide dirContestant/clientSide dirContestant/interfaces
mkdir -p dirContestant/serverSide dirContestant/serverSide/main dirContestant/clientSide/main dirContestant/clientSide/entities \
         dirContestant/interfaces
cp serverSide/main/SimulPar.class dirContestant/serverSide/main
cp clientSide/main/ClientTheGameContestant.class dirContestant/clientSide/main
cp clientSide/entities/Contestant.class clientSide/entities/ContestantState.class dirContestant/clientSide/entities
cp interfaces/BenchInterface.class interfaces/PlaygroundInterface.class interfaces/RefereeSiteInterface.class interfaces/GeneralRepoInterface.class interfaces/ReturnBoolean.class interfaces/ReturnInt.class dirContestant/interfaces

echo "  Referee"
rm -rf dirReferee/serverSide dirReferee/clientSide dirReferee/interfaces
mkdir -p dirReferee/serverSide dirReferee/serverSide/main dirReferee/clientSide/main dirReferee/clientSide/entities \
         dirReferee/interfaces
cp serverSide/main/SimulPar.class dirReferee/serverSide/main
cp clientSide/main/ClientTheGameReferee.class dirReferee/clientSide/main
cp clientSide/entities/Referee.class clientSide/entities/RefereeState.class dirReferee/clientSide/entities
cp interfaces/BenchInterface.class interfaces/PlaygroundInterface.class interfaces/RefereeSiteInterface.class interfaces/GeneralRepoInterface.class interfaces/ReturnBoolean.class interfaces/ReturnInt.class dirReferee/interfaces

echo "  Coach"
rm -rf dirCoach/serverSide dirCoach/clientSide dirCoach/interfaces
mkdir -p dirCoach/serverSide dirCoach/serverSide/main dirCoach/clientSide/main dirCoach/clientSide/entities \
         dirCoach/interfaces
cp serverSide/main/SimulPar.class dirCoach/serverSide/main
cp clientSide/main/ClientTheGameCoach.class dirCoach/clientSide/main
cp clientSide/entities/Coach.class clientSide/entities/CoachState.class dirCoach/clientSide/entities
cp interfaces/BenchInterface.class interfaces/PlaygroundInterface.class interfaces/RefereeSiteInterface.class interfaces/GeneralRepoInterface.class interfaces/ReturnBoolean.class interfaces/ReturnInt.class dirCoach/interfaces

echo "Compressing execution environments."

echo "  RMI registry"
rm -f  dirRMIRegistry.zip
zip -rq dirRMIRegistry.zip dirRMIRegistry
echo "  Register Remote Objects"
rm -f  dirRegistry.zip
zip -rq dirRegistry.zip dirRegistry

echo "  General Repository of Information"
rm -f  dirGeneralRepo.zip
zip -rq dirGeneralRepo.zip dirGeneralRepo
echo "  Bench"
rm -f  dirBench.zip
zip -rq dirBench.zip dirBench
echo "  PlayGround"
rm -f  dirPlayground.zip
zip -rq dirPlayground.zip dirPlayground
echo "  RefereeSite"
rm -f  dirRefereeSite.zip
zip -rq dirRefereeSite.zip dirRefereeSite
echo "  Contestant"
rm -f  dirContestant.zip
zip -rq dirContestant.zip dirContestant
echo "  Referee"
rm -f  dirReferee.zip
zip -rq dirReferee.zip dirReferee
echo "  Coach"
rm -f  dirCoach.zip
zip -rq dirCoach.zip dirCoach