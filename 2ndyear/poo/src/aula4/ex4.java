package aula4;
import java.util.Scanner;

public class ex4 {
    public static void main(String [] args){
    Scanner ler = new Scanner(System.in);
    int ano,dias,mes;
    int dia_semana; 
    int[] diasMesComum = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String mesExtenso="";
    String [] dia = {"seg","ter","qua","qui","sex","sab"};
    do{
        System.out.printf("Insira um mes:\n");
        mes=ler.nextInt();
        switch(mes){
            case 1:
            mesExtenso="Janeiro";
            break;
            case 2:
            mesExtenso="Fevereiro";
            break;
            case 3:
            mesExtenso="Março";
            break;
            case 4:
            mesExtenso="Abril";
            break;
            case 5:
            mesExtenso="Maio";
            break;
            case 6:
            mesExtenso="Junho";
            break;
            case 7:
            mesExtenso="Julho";
            break;
            case 8:
            mesExtenso="Agosto";
            break;
            case 9:
            mesExtenso="Setembro";
            break;
            case 10:
            mesExtenso="Outubro";
            break;
            case 11:
            mesExtenso="Novembro";
            break;
            case 12:
            mesExtenso="Dezembro";
            break;
        }
    }while(mes<0||mes>12);

    do{    
    System.out.printf("Insira um ano:\n");
        ano=ler.nextInt();
    }while(ano < 0);


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

    do{
        System.out.println("Qual o dia da semana em que comeca o mes?\nInsira um valor entre 1 e 7.");
        dia_semana=ler.nextInt();
        switch(dia_semana){
            case 1:
            dia_semana=1;
            break;
            case 2:
            dia_semana=2;
            break;
            case 3:
            dia_semana=3;
            break;
            case 4:
            dia_semana=4;
            break;
            case 5:
            dia_semana=5;
            break;
            case 6:
            dia_semana=6;
            break;
            case 7:
            dia_semana=7;
            break;

        }
    }while(dia_semana<0 || dia_semana>7);

   ler.close();
    System.out.printf("     %s %d\n",mesExtenso,ano);
    for(int i=0;i<dia.length;i++){
        System.out.printf("%s ",dia[i]);
    }
    System.out.println("");
    for(int i=0;i<7;i++){
        for(int k=1;k<8;k++){
            if( (i==0 && k<dia_semana) || ((i*7 + (k-dia_semana + 1)) > dias)){
                System.out.printf("%3s", " ");	
            }
            else {
                System.out.printf("%3d", (i*7 + (k-dia_semana + 1)));
            }
        }
        System.out.println();
    }

    }
}
