echo "Transfering data to the playground node."
sshpass -f password ssh sd206@l040101-ws02.ua.pt 'mkdir -p test/TheGame'
sshpass -f password ssh sd206@l040101-ws02.ua.pt 'rm -rf test/TheGame/*'
sshpass -f password scp dirPlayground.zip sd206@l040101-ws02.ua.pt:test/TheGame
echo "Decompressing data sent to the playground node."
sshpass -f password ssh sd206@l040101-ws02.ua.pt 'cd test/TheGame ; unzip -uq dirPlayground.zip'
sshpass -f password scp genclass.zip l040101-ws02.ua.pt:Desktop/TheGame/dirPlayground
sshpass -f password ssh sd206@l040101-ws02.ua.pt 'cd test/TheGame/dirPlayground ; unzip -uq genclass.zip'
echo "Executing program at the playground node."
sshpass -f password ssh sd206@l040101-ws02.ua.pt "cd test/TheGame/dirPlayground ; java serverSide.main.ServerPlayground 22251 l040101-ws06.ua.pt 22253"

