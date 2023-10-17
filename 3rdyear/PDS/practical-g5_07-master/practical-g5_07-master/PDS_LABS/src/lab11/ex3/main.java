package lab11.ex3;
import java.util.*;

public class main {

    public static void main(String[] args) {

        Book book1 = new Book("Java Anti-Stress", "1234567890", 2021, "Omodionah");
        Book book2 = new Book("A Guerra dos Padrões", "0987654321", 2020, "Jorge Omel");
        Book book3 = new Book("A Procura da Luz", "2468135790", 2019, "Khumatkli");

        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        Map<Integer, Runnable> operations = new HashMap<>();

        String menu = "*** Biblioteca ***";
        String options = ">> <livro>, <operação>:  (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela\n 'exit' para sair";
        Scanner scanner = new Scanner(System.in);
        String input = "";

        do {
            try {
                System.out.println(menu);
                for (Book b : books) {
                    System.out.println((books.indexOf(b) + 1) + " " + b.toString());
                }
                System.out.println(options);

                input = scanner.nextLine();

                if(input.equalsIgnoreCase("exit")) {
                    break;
                }

                String[] parts = input.split(",");

                if (parts.length != 2) {
                    throw new IllegalArgumentException("Introduza um ID e uma operação válida: <ID livro>,<número operação>");
                }
                
                int bookId = Integer.parseInt(parts[0]);
                int operationId = Integer.parseInt(parts[1]);

                if (bookId < 1 || bookId > books.size()) {
                    throw new IllegalArgumentException("Introduza um ID válido");
                }

                if (operationId < 1 || operationId > 5) {
                    throw new IllegalArgumentException("Introduza uma operação válida.");
                }
                
                Book bk = books.get(bookId - 1);
                
                operations.put(1, bk::register);
                operations.put(2, bk::request);
                operations.put(3, bk::returnBook);
                operations.put(4, bk::reservation);
                operations.put(5, bk::cancelReservation);

                if (operations.containsKey(operationId)) {
                    operations.get(operationId).run();
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Insira números válidos.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!input.equalsIgnoreCase("exit"));
        
        scanner.close();
    }
}
