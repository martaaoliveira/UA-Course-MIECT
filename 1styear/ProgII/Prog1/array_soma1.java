import java.util.*;    
public class array_soma1 {
  static Scanner read = new Scanner(System.in);
public static double calcMedia1(int a[], int n){
    int soma = 0;
    double m;
    for(int i = 0 ; i < n ; i++)
      soma += a[i];
    
    m = (double)soma / n;
    return m;
  }
public static double calcMedia2(int a[], int n) {
   int soma = 0; 
   for(int i = 0; i < n ;soma += a[i++]);
    return (double)soma / n;
  }
public static void main (String args[])       {
  int b[]={10,20,30,40,50};
  System.out.println(calcMedia1(b, b.length));
  System.out.println(calcMedia2(b, b.length));
} }
