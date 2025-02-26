echo "Transfering data to the Contestant node."
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'mkdir -p test/ClientTheGameContestant'
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'rm -rf test/ClientTheGameContestant/*'
sshpass -f password scp dirContestants.zip sd206@l040101-ws09.ua.pt:test/ClientTheGameContestant
echo "Decompressing data sent to the Contestant node."
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'cd test/ClientTheGameContestant ; unzip -uq dirContestants.zip'
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'cd test/ClientTheGameContestant/dirContestants ; '
echo "Executing program at the Contestant node."
sshpass -f password ssh sd206@l040101-ws09.ua.pt "cd test/ClientTheGameContestant/dirContestants ; java clientSide.main.ClientTheGameContestant l040101-ws05.ua.pt l040101-ws02.ua.pt l040101-ws01.ua.pt 22252 22251 22250 l040101-ws06.ua.pt 22253"