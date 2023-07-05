package aula3;

import java.util.Scanner;

public class ex6 {
    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        int ano,mes,dias=0;
        int[] diasMesComum = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        do{
            System.out.printf("Insira um mes:\n");
            mes=ler.nextInt();
        }while(mes < 0 || mes >13);

        do{    
        System.out.printf("Insira um ano:\n");
            ano=ler.nextInt();
        }while(ano < 0);

        ler.close();
        
        boolean bissexto = (ano%4 == 0 && ano%100 != 0 || ano%400 == 0);
        if (bissexto){
            if(mes == 2){
                dias = 29;
            }else{
            dias = diasMesComum[mes-1];
            }
        }else{
            dias =diasMesComum[mes-1]; 
        }
        System.out.printf("nesse ano, esse mes tem %d dias",dias);
    }
    
}
