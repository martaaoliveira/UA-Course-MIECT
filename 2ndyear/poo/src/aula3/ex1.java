package aula3;
import java.util.*;
import java.lang.Math;
public class ex1 {
    public static void main(String[] args){
        try{
        Scanner ler = new Scanner(System.in);
        double notaP,notaT,notaF;
        do{
        System.out.print("Nota da componente pratica de 0 a 20: ");
        notaP = ler.nextDouble();
        }while(notaP < 0 || notaP > 20);
        do{
        System.out.print("Nota da componente teórica de 0 a 20: ");
        notaT = ler.nextDouble();
        }while((notaT <0 || notaT > 20));
        ler.close();
        if(notaP < 7.0 || notaT < 7.0){
            System.out.println("66 (reprovado por nota minima)");
        }
        else{
            notaF = Math.round(0.4 * notaT + 0.6 * notaP);
            System.out.printf("Nota final: %.1f ", notaF);
        }

        }catch(Exception e){
           System.out.println("Erro: Insira uma nota válida");
        }
        
    }
    
}
