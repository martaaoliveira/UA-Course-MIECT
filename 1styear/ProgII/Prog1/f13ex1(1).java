import java.util.*;
import java.io.*;

public class f13ex1 {
	
	static Scanner ler = new Scanner (System.in);
	
	
	public static void main (String[] args) throws IOException{
		
		
		int n;
		meteo[]a =new meteo[31];
		
		
		do{
			System.out.print("\n\nEstação metereológica:");
			System.out.print("\n1 - Ler ficheiro de dados");
			System.out.print("\n2 - Acrescentar medida");
			System.out.print("\n3 - Listar valores de temperatura e humidade");
			System.out.print("\n4 - Listar medidas ordenadas por valor de temperatura");
			System.out.print("\n5 - Listar medidas ordenadas por valor de humidade");
			System.out.print("\n6 - Calcular valores médios de temperatura e humidade");
			System.out.print("\n7 - Calcular valores máximos e mínimos de temperatura e humidade");
			System.out.print("\n8 - Calcular histograma das temperaturas e humidade");
			System.out.print("\n9 - Terminar programa");
			System.out.print("\nOpção ->");
			
			
			n=ler.nextInt();
			
			switch(n){
			case 1:
				readFile(a);
				break;
			case 2:
				addData(a);
				break;
				
				
			default: 
			System.out.println("Inválido");
				break;
			}
			
	}while(n!=9);
	
	
}


public static void readFile(meteo[]a) throws IOException{
		String fich;
		File fichDad;
		int i=0;
		
		do{
			System.out.print("\n\nQual é o nome do ficheiro que deseja ler? ");
			fich=ler.nextLine();
			fichDad = new File(fich);
		}while(!fichDad.canRead()||!fichDad.exists()||!fichDad.isFile());
		Scanner lerFil = new Scanner(fichDad);
		while (lerFil.hasNextLine() && i<a.length){
			
			a[i]=new meteo();
			
			a[i].t=lerFil.nextInt();
			a[i].h=lerFil.nextInt();
			i++;
		}
		
		lerFil.close();
	}
	
public static void addData (meteo[]a){
	int temp;
	
	
	do{
			System.out.print("\n\nInsira uma nova temperatura: ");
			temp=ler.nextInt();
		}while(temp<-10 || temp>40);
		a[n].temp=temp;
		
		do{
			System.out.print("\nInsira a humidade: ");
			temp=ler.nextInt();
		}while(temp<0||temp>100);
		a[n].hum=temp;
	
}

}


class meteo{
	int t,h;
}

