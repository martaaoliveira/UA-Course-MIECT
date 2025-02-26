echo "Transfering data to the Referee node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'mkdir -p test/ClientTheGameReferee'
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'rm -rf test/ClientTheGameReferee/*'
sshpass -f password scp dirReferee.zip sd206@l040101-ws08.ua.pt:test/ClientTheGameReferee
echo "Decompressing data sent to the Referee node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'cd test/ClientTheGameReferee ; unzip -uq dirReferee.zip'
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'cd test/ClientTheGameReferee/dirReferee ; '
sshpass -f password scp config sd206@l040101-ws08.ua.pt:test/ClientTheGameReferee/dirReferee
echo "Executing program at the Referee node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt "cd test/ClientTheGameReferee/dirReferee ; ./referee_com_d.sh"