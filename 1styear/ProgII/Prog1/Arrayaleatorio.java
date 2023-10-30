import java.util.*;                             
public class Arrayaleatorio {
  static Random rd = new Random();
public static void main (String args[])       {
  int i=0, a[] = new int[rd.nextInt(10)];
  while(i<a.length) a[i++]=rd.nextInt(100);
  System.out.println("Elementos de array:");
  for(i=0; i<a.length; i++)
     System.out.printf("a[%d] = %d\n",i,a[i]);
}                                                           
}
