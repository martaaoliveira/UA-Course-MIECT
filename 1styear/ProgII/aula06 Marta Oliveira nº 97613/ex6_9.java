
public class ex6_9 {
static float juro;
static double P =0;
	public static void main (String[] args) {
		
		
		double T=0,M=0;
		int n=0;
		
		try
		{
			n=Integer.parseInt(args[0]);
			M=Double.parseDouble(args[1]);
			T=Double.parseDouble(args[2]);
			P=Double.parseDouble(args[3]);
			
			
			double div = M;
			juro = 1+(float)T/100;
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
		{
			System.out.println("Valores de entrada incorretos");
			System.exit(1);
		}
		System.out.println("Método iterativo" + itDn(M,T,P,n));
		System.out.println("Método recursivo" + recDn(M,T,P,n));
	}
	
	
	
	public static double itDn(double M, double T, double P , int n ){
		double div=M;
		for(int i = 1; i <= n;i++)
			div = div * juro - P;
		if(div < 0){
			System.out.print("\nImpossivel pagar a divida\n");
			System.exit(1);
		}
		
		
		return div;
		
	}
	
	
	public static double recDn(double M, double T, double P , int n){
		double div=M;
		div = getDiv(M, n);
		if(div < 0){
			System.out.print("\nImpossivel pagar a divida\n");
			System.exit(1);
		}
		System.out.printf("(recursivo) d(%d) = %.10f\n", n, div);
	return div;
}

static double getDiv(double M, int n){
		
		if(n == 0) return M;
		else return getDiv(M, n-1) * juro - P;
	}

}

