echo "Transfering data to the Coach node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'mkdir -p test/ClientTheGameCoach'
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'rm -rf test/ClientTheGameCoach/*'
sshpass -f password scp dirCoach.zip sd206@l040101-ws08.ua.pt:test/ClientTheGameCoach
echo "Decompressing data sent to the Coach node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'cd test/ClientTheGameCoach ; unzip -uq dirCoach.zip'
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'cd test/ClientTheGameCoach/dirCoach ; '
sshpass -f password ssh sd206@l040101-ws08.ua.pt 'cd test/ClientTheGameCoach/dirCoach ; unzip -uq genclass.zip'
sshpass -f password scp config sd206@l040101-ws08.ua.pt:test/ClientTheGameCoach/dirCoach
echo "Executing program at the Coach node."
sshpass -f password ssh sd206@l040101-ws08.ua.pt "cd test/ClientTheGameCoach/dirCoach ; ./coach_com_d.sh"