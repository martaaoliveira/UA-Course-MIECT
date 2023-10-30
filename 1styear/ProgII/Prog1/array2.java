import java.util.*; 
public class array2 {
  static Scanner read = new Scanner(System.in);
public static void main (String args[])       {
  System.out.print("Quantos elementos ?   ");
  int N = read.nextInt(); int a[] = new int[N];
  for(int i=0; i<a.length;i++)
  {                System.out.print("elemento "+i+"  ");
	 a[i] = read.nextInt();        }
  System.out.println("Elementos por ordem inverso:");
  for(int i=a.length-1; i>=0;i--)
     System.out.printf("a[%d] = %d\n",i,a[i]);
}
}

