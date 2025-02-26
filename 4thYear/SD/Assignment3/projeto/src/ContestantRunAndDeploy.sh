echo "Transfering data to the Contestant node."
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'mkdir -p test/ClientTheGameContestant'
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'rm -rf test/ClientTheGameContestant/*'
sshpass -f password scp dirContestant.zip sd206@l040101-ws09.ua.pt:test/ClientTheGameContestant
echo "Decompressing data sent to the Contestant node."
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'cd test/ClientTheGameContestant ; unzip -uq dirContestant.zip'
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'cd test/ClientTheGameContestant/dirContestant ; '
sshpass -f password ssh sd206@l040101-ws09.ua.pt 'cd test/ClientTheGameContestant/dirContestant ; unzip -uq genclass.zip'
sshpass -f password scp config sd206@l040101-ws09.ua.pt:test/ClientTheGameContestant/dirContestant
echo "Executing program at the Contestant node."
sshpass -f password ssh sd206@l040101-ws09.ua.pt "cd test/ClientTheGameContestant/dirContestant ; ./contestants_com_d.sh" 