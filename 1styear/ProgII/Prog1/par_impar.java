import java.util.*;    
public class par_impar {
  static Random rd = new Random();
public static void main (String args[])       {
  int a[]=null;	a=ArrayAleatorio();
  for(int x: a) System.out.println(x);
  System.out.println("impar:");
  for(int x: par(a) ) System.out.println(x);
  System.out.println("par:");
  for(int x: impar(a) ) System.out.println(x);	} 
  

public static int[] ArrayAleatorio()       {
  int[] a = new int[rd.nextInt(15)];	int i=0;
  while(i<a.length) a[i++]=rd.nextInt(100);
  return a;	
  		}
  		
public static int[] impar(int[] a)       	{
  int s = 0;
  for(int i=0; i<a.length;i++)	if(a[i] % 2 == 0) s++;
  int[] b = new int[s];
  s = 0;
  for(int i=0; i<a.length;i++)      	if(a[i] % 2 == 0) b[s++] = a[i];    
  return b;			}
  
  
public static int[] par(int[] a)       {
  int s = 0;
  for(int i=0; i<a.length;i++)      	if(a[i] % 2 != 0) s++;
  int[] b = new int[s];
  s = 0;
  for(int i=0; i<a.length;i++)      	if(a[i] % 2 != 0) b[s++] = a[i];    
  return b;
}
}
