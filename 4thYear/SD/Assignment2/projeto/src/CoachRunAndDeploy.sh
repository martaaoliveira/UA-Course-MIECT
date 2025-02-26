echo "Transfering data to the Coach node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'mkdir -p test/ClientTheGameCoach'
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'rm -rf test/ClientTheGameCoach/*'
sshpass -f password scp dirCoach.zip sd206@l040101-ws08.ua.pt:test/ClientTheGameCoach
echo "Decompressing data sent to the Coach node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'cd test/ClientTheGameCoach ; unzip -uq dirCoach.zip'
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'cd test/ClientTheGameCoach/dirCoach ; '
echo "Executing program at the Coach node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt "cd test/ClientTheGameCoach/dirCoach ; java clientSide.main.ClientTheGameCoach l040101-ws05.ua.pt l040101-ws02.ua.pt l040101-ws01.ua.pt 22252 22251 22250 l040101-ws06.ua.pt 22253"