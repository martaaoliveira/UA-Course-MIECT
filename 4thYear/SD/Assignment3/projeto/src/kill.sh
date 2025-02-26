sshpass -f password ssh sd206@l040101-ws01.ua.pt 'killall -9 -u sd206 java'

sshpass -f password ssh sd206@l040101-ws05.ua.pt 'killall -9 -u sd206 java'

sshpass -f password ssh sd206@l040101-ws06.ua.pt 'killall -9 -u sd206 java'

sshpass -f password ssh sd206@l040101-ws07.ua.pt 'killall -9 -u sd206 java'

sshpass -f password ssh sd206@l040101-ws08.ua.pt 'killall -9 -u sd206 java'

sshpass -f password ssh sd206@l040101-ws09.ua.pt 'killall -9 -u sd206 java'

sshpass -f password ssh sd206@l040101-ws10.ua.pt 'killall -9 -u sd206 java'


# Novas linhas adicionadas com obtenção automática do PID
sshpass -f password ssh sd206@l040101-ws10.ua.pt 'lsof -i :22254 | awk '\''NR>1 {print $2}'\'' | xargs -r kill -9'
sshpass -f password ssh sd206@l040101-ws07.ua.pt 'lsof -i :22251 | awk '\''NR>1 {print $2}'\'' | xargs -r kill -9'
sshpass -f password ssh sd206@l040101-ws01.ua.pt 'lsof -i :22250 | awk '\''NR>1 {print $2}'\'' | xargs -r kill -9'
sshpass -f password ssh sd206@l040101-ws05.ua.pt 'lsof -i :22252 | awk '\''NR>1 {print $2}'\'' | xargs -r kill -9'
sshpass -f password ssh sd206@l040101-ws06.ua.pt 'lsof -i :22253 | awk '\''NR>1 {print $2}'\'' | xargs -r kill -9'
