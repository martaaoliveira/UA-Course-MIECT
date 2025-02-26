echo "Transfering data to the Bench node."
sshpass -f password ssh sd206@l040101-ws05.ua.pt 'mkdir -p test/TheGame'
sshpass -f password ssh sd206@l040101-ws05.ua.pt 'rm -rf test/TheGame/*'
sshpass -f password scp dirBench.zip sd206@l040101-ws05.ua.pt:test/TheGame
echo "Decompressing data sent to the Bench node."
sshpass -f password ssh sd206@l040101-ws05.ua.pt 'cd test/TheGame ; unzip -uq dirBench.zip'
sshpass -f password ssh sd206@l040101-ws05.ua.pt 'cd test/TheGame/dirBench ; '
sshpass -f password scp config sd206@l040101-ws05.ua.pt:test/TheGame/dirBench
echo "Executing program at the Bench node."
sshpass -f password ssh sd206@l040101-ws05.ua.pt "cd test/TheGame/dirBench ; ./bench_com_d.sh sd206"