#!/bin/bash

# Muda para a pasta src
cd ../src

# Compila todos os arquivos .java
javac *.java

echo -e "\nResults:\n"
# Executa OutputJava
java OutputJava

echo -e "\nOutputJava created in src!\n"

# Abrir o arquivo
xdg-open OutputJava.java


