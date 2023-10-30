/*  Aula 6 - Recursividade
 *  NMEC: 97613		NOME: Marta Alexandra Pinheiro Oliveira
 *  Descubra o que faz a função F, implementada de forma iterativa. 
 *  Determine o caso base para o problema e a relação de recursividade
 *  de forma a implementar a função H de forma recursiva.
 */
public class Problema6 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java -ea Problema6  <n>");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);

        System.out.printf("F(%d) = %f\n", n, F(n));
        System.out.printf("H(%d) = %f\n", n, H(n));
    }

    // O que faz a função F? //serie harmonica 1/2 + 1/3 +1/4 ...
    public static double F(int n) {
        assert n >= 1;
        double r = 1.0;
        for (int i = 2; i <= n; i++) {
            r += 1.0/i;
        }
        return r;
    }

    // Implementação recursiva da função; o codigo fornecido
    // caso base: n==1
    // relação de recursividade: 1.0/n + H(n-1);
    public static double H(int n) {
       assert n>=1;
       if(n<=1){  
        return 1;
	}
        else{
			return (1.0/n + H(n-1));
		}
    }

}
