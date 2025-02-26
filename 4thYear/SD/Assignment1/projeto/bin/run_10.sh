#!/bin/bash

#cleanup actions
function cleanup {
    echo -e "\nCleaning up before terminating..."
    # Limpa arquivos .log na pasta logs
    rm -f logs/*.log
    exit 0
}

trap cleanup SIGINT

cd "$(dirname "$0")"

# Cria o diretório "logs" se ele não existir
if [ ! -d "logs" ]; then
    mkdir -p logs
fi

# Compila os arquivos Java
javac -d ../bin -cp ../lib/genclass.jar:./entities:./sharedRegion main/theGame.java entities/*.java sharedRegion/*.java main/*.java

# Execute o programa principal 10 vezes
for i in {1..10}
do
   echo "Execução $i"
   filename="logs/run$i.log"
   echo "logs/run$i.log"| java -cp ../bin:../lib/genclass.jar main.theGame > "$filename"
done
