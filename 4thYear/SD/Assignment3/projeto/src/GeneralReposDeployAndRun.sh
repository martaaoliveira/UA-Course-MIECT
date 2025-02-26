echo "Transfering data to the general repository node."
sshpass -f password ssh sd206@l040101-ws06.ua.pt 'mkdir -p test/TheGame'
sshpass -f password ssh sd206@l040101-ws06.ua.pt 'rm -rf test/TheGame/*'
sshpass -f password scp dirGeneralRepo.zip sd206@l040101-ws06.ua.pt:test/TheGame
echo "Decompressing data sent to the general repository node."
sshpass -f password ssh sd206@l040101-ws06.ua.pt 'cd test/TheGame ; unzip -uq dirGeneralRepo.zip'
sshpass -f password ssh sd206@l040101-ws06.ua.pt 'cd test/TheGame/dirGeneralRepo ; '
sshpass -f password ssh sd206@l040101-ws06.ua.pt 'cd test/TheGame/dirGeneralRepo ; unzip -uq genclass.zip'
sshpass -f password scp config sd206@l040101-ws06.ua.pt:test/TheGame/dirGeneralRepo
echo "Executing program at the server general repository."
sshpass -f password ssh sd206@l040101-ws06.ua.pt "cd test/TheGame/dirGeneralRepo ; ./generalrepo_com_d.sh sd206"
echo "Server shutdown."
sshpass -f password ssh sd206@l040101-ws06.ua.pt 'cd test/TheGame/dirGeneralRepo ; less logger'