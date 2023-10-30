import java.util.*;    
public class array_function {
  static Scanner read = new Scanner(System.in);
     
   public static void main (String args[])       {
  int a[] = new int[10];
  System.out.printf("%d valores lidos\n",lerSequencia(a));
  for(int i=0; i<a.length ;i++)
     if(a[i]==0) continue;
     else System.out.println(a[i]);
}
      
      
      
      
 // Leitura de valores até aparecer o zero
       
public static int lerSequencia(int a[]){
  int n = 0, tmp;
  do{
    System.out.print("Valor inteiro: ");
    tmp = read.nextInt();
    if(tmp != 0)
      a[n++] = tmp; // armazenamos o valor na posição n
                               // e "avançamos" para a próxima posição
  }while(tmp != 0 && n < a.length); 
  return n;   // devolvemos o número de valores lidos
}
 }
