echo "Compiling source code."
javac -cp ".:./genclass.jar" */*.java */*/*.java
echo "Distributing intermediate code to the different execution environments."
echo "  General Repository of Information"
rm -rf dirGeneralRepo
mkdir -p dirGeneralRepo dirGeneralRepo/serverSide dirGeneralRepo/serverSide/main dirGeneralRepo/serverSide/entities dirGeneralRepo/serverSide/sharedRegion \
         dirGeneralRepo/clientSide dirGeneralRepo/clientSide/entities dirGeneralRepo/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerGeneralRepos.class dirGeneralRepo/serverSide/main
cp serverSide/entities/GeneralReposClientProxy.class dirGeneralRepo/serverSide/entities
cp serverSide/sharedRegion/GeneralReposInterface.class serverSide/sharedRegion/GeneralRepos.class dirGeneralRepo/serverSide/sharedRegion
cp clientSide/entities/ContestantState.class clientSide/entities/CoachState.class clientSide/entities/RefereeState.class dirGeneralRepo/clientSide/entities
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ServerCom.class dirGeneralRepo/commInfra
echo "  Bench"
rm -rf dirBench
mkdir -p dirBench dirBench/serverSide dirBench/serverSide/main dirBench/serverSide/entities dirBench/serverSide/sharedRegion \
         dirBench/clientSide dirBench/clientSide/entities dirBench/clientSide/stubs dirBench/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerBench.class dirBench/serverSide/main
cp serverSide/entities/BenchClientProxy.class dirBench/serverSide/entities
cp serverSide/sharedRegion/GeneralReposInterface.class serverSide/sharedRegion/BenchInterface.class serverSide/sharedRegion/Bench.class dirBench/serverSide/sharedRegion
cp clientSide/entities/ContestantState.class clientSide/entities/CoachState.class clientSide/entities/RefereeState.class clientSide/entities/ContestantCloning.class clientSide/entities/CoachCloning.class clientSide/entities/RefereeCloning.class \
   dirBench/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirBench/clientSide/stubs
cp commInfra/*.class dirBench/commInfra
echo "  Playground"
rm -rf dirPlayground
mkdir -p dirPlayground dirPlayground/serverSide dirPlayground/serverSide/main dirPlayground/serverSide/entities dirPlayground/serverSide/sharedRegion \
         dirPlayground/clientSide dirPlayground/clientSide/entities dirPlayground/clientSide/stubs dirPlayground/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerBench.class dirPlayground/serverSide/main
cp serverSide/entities/BenchClientProxy.class dirPlayground/serverSide/entities
cp serverSide/sharedRegion/GeneralReposInterface.class serverSide/sharedRegion/BenchInterface.class serverSide/sharedRegion/Bench.class dirPlayground/serverSide/sharedRegion
cp clientSide/entities/ContestantState.class clientSide/entities/CoachState.class clientSide/entities/RefereeState.class clientSide/entities/ContestantCloning.class clientSide/entities/CoachCloning.class clientSide/entities/RefereeCloning.class \
   dirPlayground/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirPlayground/clientSide/stubs
cp commInfra/*.class dirPlayground/commInfra
echo "  refereeSite"
rm -rf dirRefereeSite
mkdir -p dirRefereeSite dirRefereeSite/serverSide dirRefereeSite/serverSide/main dirRefereeSite/serverSide/entities dirRefereeSite/serverSide/sharedRegion \
         dirRefereeSite/clientSide dirRefereeSite/clientSide/entities dirRefereeSite/clientSide/stubs dirRefereeSite/commInfra
cp serverSide/main/SimulPar.class serverSide/main/ServerBench.class dirRefereeSite/serverSide/main
cp serverSide/entities/BenchClientProxy.class dirRefereeSite/serverSide/entities
cp serverSide/sharedRegion/GeneralReposInterface.class serverSide/sharedRegion/BenchInterface.class serverSide/sharedRegion/Bench.class dirRefereeSite/serverSide/sharedRegion
cp clientSide/entities/ContestantState.class clientSide/entities/CoachState.class clientSide/entities/RefereeState.class clientSide/entities/ContestantCloning.class clientSide/entities/CoachCloning.class clientSide/entities/RefereeCloning.class \
   dirRefereeSite/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirRefereeSite/clientSide/stubs
cp commInfra/*.class dirRefereeSite/commInfra
echo "  Coach"
rm -rf dirCoach
mkdir -p dirCoach dirCoach/serverSide dirCoach/serverSide/main dirCoach/clientSide dirCoach/clientSide/main dirCoach/clientSide/entities \
         dirCoach/clientSide/stubs dirCoach/commInfra
cp serverSide/main/SimulPar.class dirCoach/serverSide/main
cp clientSide/main/ClientTheGameCoach.class dirCoach/clientSide/main
cp clientSide/entities/Contestant.class clientSide/entities/ContestantState.class dirCoach/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/BenchStub.class clientSide/stubs/PlaygroundStub.class clientSide/stubs/RefereeSiteStub.class dirCoach/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirCoach/commInfra
echo "  Contestant"
rm -rf dirContestant
mkdir -p dirContestant dirContestant/serverSide dirContestant/serverSide/main dirContestant/clientSide dirContestant/clientSide/main dirContestant/clientSide/entities \
         dirContestant/clientSide/stubs dirContestant/commInfra
cp serverSide/main/SimulPar.class dirContestant/serverSide/main
cp clientSide/main/ClientTheGameContestant.class dirContestant/clientSide/main
cp clientSide/entities/Coach.class clientSide/entities/CoachState.class dirCoach/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/BenchStub.class clientSide/stubs/PlaygroundStub.class clientSide/stubs/RefereeSiteStub.class dirCoach/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirContestant/commInfra
echo "  Referee"
rm -rf dirReferee
mkdir -p dirReferee dirReferee/serverSide dirReferee/serverSide/main dirReferee/clientSide dirReferee/clientSide/main dirReferee/clientSide/entities \
         dirReferee/clientSide/stubs dirReferee/commInfra
cp serverSide/main/SimulPar.class dirReferee/serverSide/main
cp clientSide/main/ClientTheGameReferee.class dirReferee/clientSide/main
cp clientSide/entities/Referee.class clientSide/entities/RefereeState.class dirCoach/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/BenchStub.class clientSide/stubs/PlaygroundStub.class clientSide/stubs/RefereeSiteStub.class dirCoach/clientSide/stubs
cp commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirReferee/commInfra
echo "Compressing execution environments."
echo "  General Repository of Information"
rm -f  dirGeneralRepo.zip
zip -rq dirGeneralRepo.zip dirGeneralRepo
echo "  Bench"
rm -f  dirBench.zip
zip -rq dirBench.zip dirBench
echo "  playground"
rm -f  dirPlayground.zip
zip -rq dirPlayground.zip dirPlayground
echo "  refereeSite"
rm -f  dirRefereeSite.zip
zip -rq dirRefereeSite.zip dirRefereeSite
echo "  Coach"
rm -f  dirCoach.zip
zip -rq dirCoach.zip dirCoach
echo "  Contestant"
rm -f  dirContestant.zip
zip -rq dirContestant.zip dirContestant
echo "  Referee"
rm -f  dirReferee.zip
zip -rq dirReferee.zip dirReferee
echo "Deploying and decompressing execution environments."
mkdir -p /home/Desktop/TheGame
rm -rf /home/Desktop/TheGame/*
cp dirGeneralRepo.zip /home/Desktop/TheGame
cp dirBench.zip /home/Desktop/TheGame
cp dirPlayground.zip /home/Desktop/TheGame
cp dirRefereeSite.zip /home/Desktop/TheGame
cp dirCoach.zip /home/Desktop/TheGame
cp dirContestant.zip /home/Desktop/TheGame
cp dirReferee.zip /home/Desktop/TheGame
cd /home/Desktop/TheGame
unzip -q dirGeneralRepo.zip
unzip -q dirBench.zip
unzip -q dirPlayground.zip
unzip -q dirRefereeSite.zip
unzip -q dirCoach.zip
unzip -q dirContestant.zip
unzip -q dirReferee.zip