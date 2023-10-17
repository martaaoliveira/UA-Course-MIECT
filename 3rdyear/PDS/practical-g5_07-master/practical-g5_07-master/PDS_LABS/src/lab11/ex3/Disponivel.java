package lab11.ex3;

public class Disponivel implements State {
    
        @Override
        public void register(Book book) {
            System.out.println("Não pode ser registado");
        }
    
        @Override
        public void request(Book book) {
            System.out.println("Requisitado");
            book.setState(new Emprestado());
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
            book.setState(new Reservado());
        }

        public  String toString(){
            return "[Disponível]";
        }
    
}
