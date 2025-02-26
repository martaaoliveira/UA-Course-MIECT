echo "Compiling source code."
javac -cp ".:./genclass.jar" */*.java */*/*.java
echo "Distributing intermediate code to the different execution environments."
echo "  General Repository of Information"
rm -rf dirGeneralRepo
mkdir -p dirGeneralRepo dirGeneralRepo/serverSide dirGeneralRepo/serverSide/main dirGeneralRepo/serverSide/entities dirGeneralRepo/serverSide/sharedRegion \
         dirGeneralRepo/clientSide dirGeneralRepo/clientSide/entities dirGeneralRepo/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerGeneralRepos.class dirGeneralRepo/serverSide/main
cp serverSide/entities/GeneralReposClientProxy.class dirGeneralRepo/serverSide/entities
cp serverSide/sharedRegion/GeneralRepoInterface.class serverSide/sharedRegion/GeneralRepo.class dirGeneralRepo/serverSide/sharedRegion
cp clientSide/entities/ContestantState.class clientSide/entities/RefereeState.class clientSide/entities/CoachState.class dirGeneralRepo/clientSide/entities
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ServerCom.class dirGeneralRepo/commInfra
echo "  Bench"
rm -rf dirBench
mkdir -p dirBench dirBench/serverSide dirBench/serverSide/main dirBench/serverSide/entities dirBench/serverSide/sharedRegion \
         dirBench/clientSide dirBench/clientSide/entities dirBench/clientSide/stubs dirBench/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerBench.class dirBench/serverSide/main
cp serverSide/entities/BenchClientProxy.class dirBench/serverSide/entities
cp serverSide/sharedRegion/GeneralRepoInterface.class serverSide/sharedRegion/BenchInterface.class serverSide/sharedRegion/Bench.class dirBench/serverSide/sharedRegion
cp clientSide/entities/ContestantState.class clientSide/entities/Contestant.class clientSide/entities/RefereeState.class clientSide/entities/CoachState.class clientSide/entities/ContestantCloning.class clientSide/entities/RefereeCloning.class clientSide/entities/CoachCloning.class \
   dirBench/clientSide/entities
cp clientSide/stubs/GeneralRepoStub.class clientSide/stubs/BenchStub.class clientSide/stubs/RefereeSiteStub.class clientSide/stubs/PlaygroundStub.class dirBench/clientSide/stubs
cp commInfra/*.class dirBench/commInfra


echo "  Playground"
rm -rf dirPlayground
mkdir -p dirPlayground dirPlayground/serverSide dirPlayground/serverSide/main dirPlayground/serverSide/entities dirPlayground/serverSide/sharedRegion \
         dirPlayground/clientSide dirPlayground/clientSide/entities dirPlayground/clientSide/stubs dirPlayground/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerPlayground.class dirPlayground/serverSide/main
cp serverSide/entities/PlaygroundClientProxy.class dirPlayground/serverSide/entities
cp serverSide/sharedRegion/GeneralRepoInterface.class serverSide/sharedRegion/playgroundInterface.class serverSide/sharedRegion/playground.class dirPlayground/serverSide/sharedRegion
cp clientSide/entities/ContestantState.class clientSide/entities/RefereeState.class clientSide/entities/Coach.class clientSide/entities/CoachState.class clientSide/entities/ContestantCloning.class clientSide/entities/RefereeCloning.class clientSide/entities/CoachCloning.class \
   dirPlayground/clientSide/entities
cp clientSide/stubs/GeneralRepoStub.class clientSide/stubs/BenchStub.class clientSide/stubs/RefereeSiteStub.class clientSide/stubs/PlaygroundStub.class dirPlayground/clientSide/stubs
cp commInfra/*.class dirPlayground/commInfra


echo "  RefereeSite"
rm -rf dirRefereeSite
mkdir -p dirRefereeSite dirRefereeSite/serverSide dirRefereeSite/serverSide/main dirRefereeSite/serverSide/entities dirRefereeSite/serverSide/sharedRegion \
         dirRefereeSite/clientSide dirRefereeSite/clientSide/entities dirRefereeSite/clientSide/stubs dirRefereeSite/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerRefereeSite.class dirRefereeSite/serverSide/main
cp serverSide/entities/RefereeSiteClientProxy.class dirRefereeSite/serverSide/entities
cp serverSide/sharedRegion/GeneralRepoInterface.class serverSide/sharedRegion/refereeSiteInterface.class serverSide/sharedRegion/refereeSite.class dirRefereeSite/serverSide/sharedRegion
cp clientSide/entities/ContestantState.class clientSide/entities/RefereeState.class clientSide/entities/Coach.class clientSide/entities/CoachState.class clientSide/entities/ContestantCloning.class clientSide/entities/RefereeCloning.class clientSide/entities/CoachCloning.class \
   dirRefereeSite/clientSide/entities
cp clientSide/stubs/GeneralRepoStub.class clientSide/stubs/BenchStub.class clientSide/stubs/RefereeSiteStub.class clientSide/stubs/PlaygroundStub.class dirRefereeSite/clientSide/stubs
cp commInfra/*.class dirRefereeSite/commInfra

echo "  Contestants"
rm -rf dirContestants
mkdir -p dirContestants dirContestants/serverSide dirContestants/serverSide/main dirContestants/clientSide dirContestants/clientSide/main dirContestants/clientSide/entities \
         dirContestants/clientSide/stubs dirContestants/commInfra
cp serverSide/main/SimulPar.class dirContestants/serverSide/main

cp clientSide/main/ClientTheGameContestant.class dirContestants/clientSide/main

cp clientSide/entities/Contestant.class clientSide/entities/ContestantState.class clientSide/entities/Coach.class clientSide/entities/Referee.class  dirContestants/clientSide/entities

cp clientSide/stubs/GeneralRepoStub.class clientSide/stubs/BenchStub.class clientSide/stubs/RefereeSiteStub.class clientSide/stubs/PlaygroundStub.class dirContestants/clientSide/stubs

cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirContestants/commInfra

echo "  Referee"
rm -rf dirReferee
mkdir -p dirReferee dirReferee/serverSide dirReferee/serverSide/main dirReferee/clientSide dirReferee/clientSide/main dirReferee/clientSide/entities \
         dirReferee/clientSide/stubs dirReferee/commInfra
cp serverSide/main/SimulPar.class dirReferee/serverSide/main
cp clientSide/main/ClientTheGameReferee.class dirReferee/clientSide/main
cp clientSide/entities/Referee.class clientSide/entities/RefereeState.class  clientSide/entities/Coach.class clientSide/entities/Contestant.class dirReferee/clientSide/entities
cp clientSide/stubs/GeneralRepoStub.class clientSide/stubs/BenchStub.class clientSide/stubs/RefereeSiteStub.class clientSide/stubs/PlaygroundStub.class dirReferee/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirReferee/commInfra

echo "  Coach"
rm -rf dirCoach
mkdir -p dirCoach dirCoach/serverSide dirCoach/serverSide/main dirCoach/clientSide dirCoach/clientSide/main dirCoach/clientSide/entities \
         dirCoach/clientSide/stubs dirCoach/commInfra
cp serverSide/main/SimulPar.class dirCoach/serverSide/main
cp clientSide/main/ClientTheGameCoach.class dirCoach/clientSide/main
cp clientSide/entities/Coach.class clientSide/entities/CoachState.class clientSide/entities/Referee.class clientSide/entities/Contestant.class dirCoach/clientSide/entities
cp clientSide/stubs/GeneralRepoStub.class clientSide/stubs/BenchStub.class clientSide/stubs/RefereeSiteStub.class clientSide/stubs/PlaygroundStub.class dirCoach/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirCoach/commInfra

echo "Compressing execution environments."

echo "  General Repository of Information"
rm -f  dirGeneralRepo.zip
zip -rq dirGeneralRepo.zip dirGeneralRepo
echo "  Bench"
rm -f  dirBench.zip
zip -rq dirBench.zip dirBench
echo "  Playground"
rm -f  dirPlayground.zip
zip -rq dirPlayground.zip dirPlayground
echo "  RefereeSite"
rm -f  dirRefereeSite.zip
zip -rq dirRefereeSite.zip dirRefereeSite
echo "  Contestant"
rm -f  dirContestants.zip
zip -rq dirContestants.zip dirContestants
echo "  Referee"
rm -f  dirReferee.zip
zip -rq dirReferee.zip dirReferee
echo "  Coach"
rm -f  dirCoach.zip
zip -rq dirCoach.zip dirCoach
