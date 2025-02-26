source config
echo "Transfering data to the registry node."
sshpass -f password ssh sd206@l040101-ws10.ua.pt 'mkdir -p test/TheGame'
sshpass -f password scp dirRegistry.zip sd206@l040101-ws10.ua.pt:test/TheGame
echo "Decompressing data sent to the registry node."
sshpass -f password ssh sd206@l040101-ws10.ua.pt 'cd test/TheGame ; unzip -uq dirRegistry.zip'
sshpass -f password scp config sd206@l040101-ws10.ua.pt:test/TheGame/dirRegistry/config
echo "Executing program at the registry node."
sshpass -f password ssh sd206@l040101-ws10.ua.pt 'cd test/TheGame/dirRegistry ; ./registry_com_d.sh sd206'
