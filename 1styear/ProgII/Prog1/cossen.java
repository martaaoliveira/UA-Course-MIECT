import java.util.*;
public class cossen {
	
	static Scanner ler= new Scanner(System.in);
	
	public static void main (String[] args) {
		double[] a= new double[10];
		int numVezes;
		double[] b=new double[10];
		
		limpa(a);
		b=cos(a);
		numVezes=zeros(b);
		System.out.println("---------------------------------------");
		System.out.printf("Existem %d zeros no intervalo definido\n",numVezes);
		System.out.println("---------------------------------------");
		tabela(b,a);
		
	}
	
	public static double[] cos(double a[]){
		double B[] = new double[10];
		for(int i=0;i<a.length;i++){
			B[i]=Math.cos(a[i]);
			System.out.printf("%.4f\n",B[i]);
		}
		return B;
	}
	
	public static double[] limpa(double a[]){
		for(int i=0;i<a.length;i++){
			System.out.printf("Numero %d -> ",i+1);
			a[i]=ler.nextDouble();
		}
		return a;
	}
	
	public static int zeros(double B[]){
		int Numvezes=0;
		
		for ( int i=0;i<B.length-1;i++){
			if(( B[i]>0 && B[i+1]<0 ) || ( B[i]<0 && B[i+1]>0 )) {
				Numvezes++;
			}
		}
		return Numvezes;
			
	}
	
	public static void tabela(double B[],double a[]){
		System.out.println("_______________________");
		System.out.println("|POS\t |x\t|cos(x)\t|");
		System.out.println("|-----------------------|");
		for(int i=0;i<B.length;i++){
		System.out.printf ("|%d\t |%3.2f\t|%2.2f\t|\n", i+1, a[i], B[i]);
		}
		System.out.println("|________|______|_______|");

		
	}
	
}
		
		
	
