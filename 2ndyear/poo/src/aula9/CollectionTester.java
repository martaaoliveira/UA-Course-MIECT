package aula9;
import java.util.*;
public class CollectionTester {
public static void main(String[] args) {
    int DIM = 5000;
    System.out.printf("%-30s%10d%10d%10d%10d%10d%10d%n", "Collection", 1000, 5000, 10000, 20000, 40000, 100000);
    Collection<Integer> col = new ArrayList<>();
    printPerformance(col);
    Collection<Integer> LL = new LinkedList<>();
    printPerformance(LL);
    Collection<Integer> HS = new HashSet<>();
    printPerformance(HS);
    Collection<Integer> TS = new TreeSet<>();
    printPerformance(TS);
    

}
    
    
    
private static double[] checkPerformance(Collection<Integer> col, int DIM) {
    double start, stop, delta;
    // Add
    double[] medicoes = new double[3];

    start = System.nanoTime(); // clock snapshot before
    for(int i=0; i<DIM; i++ )
    col.add( i );
    stop = System.nanoTime(); // clock snapshot after
    delta = (stop-start)/1e6; // convert to milliseconds
    //System.out.println(col.size()+ ": Add to " +
    //col.getClass().getSimpleName() +" took " + delta + "ms");
    // Search
    medicoes[0] = delta;
    start = System.nanoTime(); // clock snapshot before
    for(int i=0; i<DIM; i++ ) {
    int n = (int) (Math.random()*DIM);
    if (!col.contains(n))
    System.out.println("Not found???"+n);
    }
    stop = System.nanoTime(); // clock snapshot after
    delta = (stop-start)/1e6; // convert nanoseconds to milliseconds
    //System.out.println(col.size()+ ": Search to " +
    //col.getClass().getSimpleName() +" took " + delta + "ms");
    // Remove
    medicoes[1] = delta;
    start = System.nanoTime(); // clock snapshot before
    Iterator<Integer> iterator = col.iterator();
    while (iterator.hasNext()) {
     iterator.next();
     iterator.remove();
    }
    stop = System.nanoTime(); // clock snapshot after
    delta = (stop-start)/1e6; // convert nanoseconds to milliseconds
    //System.out.println(col.size() + ": Remove from "+
    //col.getClass().getSimpleName() +" took " + delta + "ms");
    medicoes[2] = delta;

    return medicoes;



}

private static void printPerformance(Collection<Integer> col){
    int[] DIM = {1000, 5000, 10000, 20000, 40000, 100000};
    System.out.printf("%n%-30s%n", col.getClass().getSimpleName());
    double[][] medicoesTotais = new double[3][6];
    int c = 0;
    for(int i: DIM){
        double[] medicaoTempo = checkPerformance(col, i);
        medicoesTotais[0][c] = medicaoTempo[0];
        medicoesTotais[1][c] = medicaoTempo[1];
        medicoesTotais[2][c] = medicaoTempo[2];
        c++;
    }
    c = 0;
        String[] metodos = {"add", "search", "remove"};
        for(double m[]: medicoesTotais){
            System.out.printf("%-30s%10.1f%10.1f%10.1f%10.1f%10.1f%10.1f%n", metodos[c], m[0], m[1], m[2], m[3], m[4], m[5]);
			c++;
        }
}
    }
