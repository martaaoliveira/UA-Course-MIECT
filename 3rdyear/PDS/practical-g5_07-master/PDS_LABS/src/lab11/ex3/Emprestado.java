package lab11.ex3;

public class Emprestado implements State {

    @Override
    public void register(Book book) {
        System.out.println("Não pode ser registado");
    }

    @Override
    public void request(Book book) {
        System.out.println("Não pode ser requisitado");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("Devolvido");
        book.setState(new Disponivel());
    }

    @Override
    public void cancelReservation(Book book) {
        System.out.println("Não pode ser cancelada a reserva");
    }

    @Override
    public void reservation(Book book) {
        System.out.println("Não pode ser reservado");
    }

    public String toString() {
        return "[Emprestado]";
    }
    
}
