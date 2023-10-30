import java.util.*;    
public class array_function1 {
  static Scanner read = new Scanner(System.in);
       public static void main (String args[])       {
 int a[]=null;
	a=lerSequencia(a);
  for(int i=0; i<a.length ;i++)
     if(a[i]!=0) System.out.println(a[i]);
}
       
       // Leitura de valores até aparecer o zero
public static int[] lerSequencia(int a[]){
  int n = 0, tmp;   a = new int[10];
  do{
    System.out.print("Valor inteiro: ");
    tmp = read.nextInt();
    if(tmp != 0)
      a[n++] = tmp; // armazenamos o valor na posição n
                               // e "avançamos" para a próxima posição
  }while(tmp != 0 && n < a.length); 
  return a;   // devolvemos referência a para array
}
 }
