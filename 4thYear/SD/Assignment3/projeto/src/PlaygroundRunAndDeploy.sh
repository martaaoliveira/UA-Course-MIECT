echo "Transfering data to the playground node."
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'mkdir -p test/TheGame'
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'rm -rf test/TheGame/*'
sshpass -f password scp dirPlayground.zip sd206@l040101-ws07.ua.pt:test/TheGame
echo "Decompressing data sent to the playground node."
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'cd test/TheGame ; unzip -uq dirPlayground.zip'
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'cd test/TheGame/dirPlayground ; '
sshpass -f password scp config sd206@l040101-ws06.ua.pt:test/TheGame/dirPlayground
echo "Executing program at the playground node."
sshpass -f password ssh sd206@l040101-ws07.ua.pt "cd test/TheGame/dirPlayground ; ./playground_com_d.sh sd206"