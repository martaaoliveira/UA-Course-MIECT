echo "Transfering data to the refereeSite node."
sshpass -f password ssh sd206@l040101-ws01.ua.pt 'mkdir -p test/TheGame'
sshpass -f password ssh sd206@l040101-ws01.ua.pt 'rm -rf test/TheGame/*'
sshpass -f password scp dirRefereeSite.zip sd206@l040101-ws01.ua.pt:test/TheGame
echo "Decompressing data sent to the refereeSite node."
sshpass -f password ssh sd206@l040101-ws01.ua.pt 'cd test/TheGame ; unzip -uq dirRefereeSite.zip'
sshpass -f password ssh sd206@l040101-ws01.ua.pt 'cd test/TheGame/dirRefereeSite ; '
sshpass -f password scp config sd206@l040101-ws01.ua.pt:test/TheGame/dirRefereeSite
echo "Executing program at the refereeSite node."
sshpass -f password ssh sd206@l040101-ws01.ua.pt "cd test/TheGame/dirRefereeSite ; java serverSide.main.ServerRefereeSite 22250 l040101-ws06.ua.pt 22253"