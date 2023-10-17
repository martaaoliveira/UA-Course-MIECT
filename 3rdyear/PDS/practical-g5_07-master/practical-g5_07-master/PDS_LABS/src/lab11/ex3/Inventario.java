package lab11.ex3;

public class Inventario implements State{

    @Override
    public void register(Book book) {
        System.out.println("Registado");
        book.setState(new Disponivel());
    }

    @Override
    public void request(Book book) {
        System.out.println("Não pode ser requisitado");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("Não pode ser devolvido");
    }

    @Override
    public void cancelReservation(Book book) {
        System.out.println("Não pode ser cancelada a reserva");
    }

    @Override
    public void reservation(Book book) {
        System.out.println("Não pode ser reservado");
    }
    
}
