import java.util.Scanner;
public class f8e2 {
	static Scanner ler=new Scanner(System.in);
	public static void main (String[] args) {
		
		Ponto2D a[]= new Ponto2D[20];
		Ponto2D o = new Ponto2D();
		o.x=0;o.y=0;
		double d;
		int i=0;
		Ponto2D p;
		do
		{
			p=lerPonto();
			if (p.x!=0 || p.y!=0)
			{
				a[i]=p;
				i++;
				d=distancia(a[i],o);
			}
			
		} while (p.x!=0 || p.y!=0);
		
		System.out.println("Pontos introduzidos");
		
		for (int j = 0; j< i; j++)
		{
			printPonto(a[j]);
		}
		
		
		
	}
		
	
	public static Ponto2D lerPonto(){
		Ponto2D pto=new Ponto2D();
		
		System.out.println("Introduza coordenada X");
		pto.x=ler.nextDouble();
		System.out.println("Introduza coordenada Y");
		pto.y=ler.nextDouble();
		
		return pto;
	}
	
	public static void printPonto(Ponto2D p){
		System.out.printf("%.1f,%.1f\n",p.x,p.y);
	}
		
	public static double distancia(Ponto2D p1,Ponto2D p2){
		double d;
		d=Math.sqrt(Math.pow((p2.x-p1.x),2)+Math.pow((p2.y-p1.y),2));
		return d;
		
	}
		
}
class Ponto2D {
	double x,y;
	
}

