echo "Transfering data to the Referee node."
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'mkdir -p test/ClientTheGameReferee'
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'rm -rf test/ClientTheGameReferee/*'
sshpass -f password scp dirReferee.zip sd206@l040101-ws07.ua.pt:test/ClientTheGameReferee
echo "Decompressing data sent to the Referee node."
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'cd test/ClientTheGameReferee ; unzip -uq dirReferee.zip'
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'cd test/ClientTheGameReferee/dirReferee ; '
echo "Executing program at the Referee node."
sshpass -f password ssh sd206@l040101-ws07.ua.pt "cd test/ClientTheGameReferee/dirReferee ; java clientSide.main.ClientTheGameReferee l040101-ws05.ua.pt l040101-ws02.ua.pt l040101-ws01.ua.pt 22252 22251 22250 l040101-ws06.ua.pt 22253"