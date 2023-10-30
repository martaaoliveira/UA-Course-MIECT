import java.util.Scanner;

public class escala {
    
    
   public static void main (String[] args) {
    Scanner scale= new Scanner(System.in);
    final int esc = 100;
        System.out.print(" introduza abcissa ponto 1:");
        double x1= scale.nextDouble();
        System.out.print (" introduza ordenada ponto 1:");
        double y1= scale.nextDouble();
        System.out.print(" introduza abcissa ponto 2:");
        double x2=scale.nextDouble();
        System.out.print("  introduza ordenada ponto 2: ");
        double y2=scale.nextDouble();
        double resu=esc*Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
        System.out.print(resu + " km");
       
   } 
    
    
    
    
    
}
