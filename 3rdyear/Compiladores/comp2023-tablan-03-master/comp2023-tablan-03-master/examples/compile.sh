#!/bin/bash

# Verifica se um argumento foi passado para o script
if [ $# -eq 0 ]; then
    echo "Por favor, passe o nome do arquivo de exemplo como um argumento."
    exit 1
fi

# Armazena o nome do arquivo de exemplo
example=$1

# Caminho para a pasta com os exemplos
path_to_examples="."

# Imprime o exemplo atual para depuração
echo "Processando exemplo: $example"

# Verifica se o arquivo de exemplo existe
if [ -f "$path_to_examples/$example" ]; then
  echo "O arquivo $example existe."
else
  echo "O arquivo $example não existe!"
  exit 1
fi

# Armazena o diretório atual
current_dir=$(pwd)

# Muda para a pasta src
cd ../src

# Executa antlr4-run para o exemplo atual e espera até que termine
antlr4-run < "$current_dir/$example"

# Volta para o diretório original
cd $current_dir
