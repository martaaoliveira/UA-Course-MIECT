package aula3;
import java.lang.Math;
public class ex8 {
    public static void main(String[] args){
        double notas[][] = new double[16][2];
		double nf;

		System.out.println("NotaT  NotaP  Pauta");
		for (int i = 0; i < notas.length; i++) {
			notas[i][0] = (Math.random()*20);
			notas[i][1] = (Math.random()*20);
		}

	for (int i = 0; i < notas.length; i++) {
		if( notas[i][0]<7.0 || notas[i][1]<7.0 ){
			nf = 66;
		}
		else{ 
		nf = Math.round(notas[i][0]*0.4 + notas[i][1]*0.6);
		}
		System.out.printf(" %4.1f   %4.1f    %3.0f\n", notas[i][0], notas[i][1], nf);
	}
    
}
}

