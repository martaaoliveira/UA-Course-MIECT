import java.util.*;    
public class array_soma {
  static Scanner read = new Scanner(System.in);
       // Leitura de valores até aparecer o zero
public static int[] soma(int a[], int b[]){
  if(a.length != b.length) return null;
  int c[] = new int[b.length]; 
  for(int i=0; i < b.length; i++)
       c[i] = a[i] + b[i];
  return c; 
}
public static void main (String args[])       {
  int a[]={1,2,3,4,5};
  int b[]={10,20,30,40,50};
  int y[] = soma(a,b);
   for(int i=0; i < y.length; i++)
       System.out.printf("%d + %d = %d\n",a[i], b[i], y[i]);
} }
