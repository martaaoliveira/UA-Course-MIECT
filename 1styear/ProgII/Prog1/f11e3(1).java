import java.util.Scanner;

public class f11e3 {
	
	static final Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) {
		Contacto[] agenda = new Contacto [100];
		int opcao, n = 0, p;
		String s;
		
		do
		{
			System.out.println("Gestão de uma agenda: ");
			System.out.println("I - Inserir um contacto");
			System.out.println("P - Pesquisar contacto por nome");
			System.out.println("E - Eliminar um contacto");
			System.out.println("M - Mostrar os contactos");
			System.out.println("O - Mostrar os contactos Ordenados pelo nome");
			System.out.println("V - Validar endereço de email");
			System.out.println("A - Apagar a agenda");
			System.out.println("T - Terminar programa");
			System.out.println("Opção -> ");
			
			do
			{
				s = sc.nextLine();
			} while (s.length() == 0); //NÃO ESQUECER!!!!
			
			opcao = s.charAt(0);
			
			switch (opcao)
			{
				case 'I':
					n = insere(agenda, n);
					break; 
				
				case 'P':
					pesquisa(agenda, n);
					break;
				
				case 'E':
					eliminar(agenda, n);
					break;
					
				case 'M':
					mostrar(agenda, n);
					break;
				
				case 'O':
					ordenar(agenda, n);
					break;
				
				case 'V':
					validar(agenda, n);
					break;
				
				case 'A':
					apagar(agenda, n);
					break;
				
				case 'T':
					System.out.println("Bye!!!");
				default:
					System.out.println("Opção Inválida!!!");
			}
			
		
		} while (opcao != 'T');
	}
	
	public static int insere (Contacto[] a, int i){
		a[i] = new Contacto(); // NÃO ESQUECER!!!!
		
		System.out.print("Nome: ");
		a[i].nome = sc.nextLine();
		
		System.out.print("Morada: ");
		a[i].morada = sc.nextLine();
		
		System.out.print("Telefone: ");
		a[i].telef = sc.nextInt();
		
		System.out.print("Email: ");
		a[i].email = sc.nextLine();
		
		return i+1;		
	}
	
	public static void pesquisa (Contacto[] a, int n) {
		System.out.print("Qual o nome que deseja procurar? ");
	}
	
	//public static int eliminar (Contacto[] a, int n) {
	//}
	
	//public static void mostrar (Contacto[] a, int n) {
	//}
	
	//public static void ordenar (Contacto[] a, int n) {
	//}
	
	//public static void validar (Contacto[] a) {
	//}
	
	//public static void apagar (Contacto[] a) {
	//}
}

class Contacto {
	String nome, morada, email;
	int telef;

}

