
# Aula01 - Notes
## 1- abrir JAVA Project

## Compilar e executar Ex1: (está funcional)
```bash
1º javac lab03/ex1/*.java
2º java lab03/ex1/JGalo
```

### Compilar e executar Ex2: (compila e executa mas com alguns bugs)
```bash
1º javac lab03/ex2/*.java
2º java lab03/ex2/main 
3º I lab03/ex2/flight1.txt //guardar informações do voo
4º F TP2000 4x3 //criar voo
5º R TP2000 T 2 
6º M TP2000
```
### Notas:

Ter em conta que, neste exercicio, o output para "I lab03/ex2/flight1.txt" não está 100% funcional

Output:
```bash
"Código de voo TP1920. 

Lugares disponíveis: 6 lugares em classe Executiva; 33 lugares em classe Turística.

Não foi possível obter lugares para a reserva: E 2"
```
Quando criamos um voo, "F TP2000 4x"3 ele é criado. Quando, no 5º passo fazemos uma reserva e de seguida, no 6º passo, exibimos o mapa de reservas desse Voo conseguimoss observar isso.


No entanto, a função que exibe as reservas também não está 100% funcional.
