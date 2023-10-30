import java.util.*;
public class array1 {
	static Scanner read = new Scanner(System.in);
 public static void main (String args[]) {		
	int[] a;
	System.out.print("Quantos elementos ?  ");
	int N = read.nextInt();
	a = new int[N];
	for(int j = a.length-1; j>=0; j--) a[j] = read.nextInt(); 
	for (int z : a) System.out.printf("%d   ",z);
	System.out.println();
	 	}
	 	
	}
