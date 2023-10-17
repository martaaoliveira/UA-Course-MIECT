package lab11.ex3;


public class Book {
   //Numa biblioteca cada livro é caracterizado por um título ISBN,ano, e o primeiro autor.A entidade livro permite operações tais como: regista, requisita, reserva, cancelaReserva,disponível, etc.). No entanto, cada uma destas operações depende da situação do livro na biblioteca
    private String title;
    private String ISBN;
    private int year;
    private String firstAuthor;
    private State state;

    public Book(String title, String ISBN, int year, String firstAuthor) {
        this.title = title;
        this.ISBN = ISBN;
        this.year = year;
        this.firstAuthor = firstAuthor;
        this.state = new Inventario();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void register() {
        state.register(this);
    }

    public void request() {
        state.request(this);
    }

    public void returnBook() {
        state.returnBook(this);
    }

    public void cancelReservation() {
        state.cancelReservation(this);
    }

    public void reservation() {
        state.reservation(this);
    }

    public String toString() {
        return "Book{" + "title=" + title + ", ISBN=" + ISBN + ", year=" + year + ", firstAuthor=" + firstAuthor + ", state=" + state + '}';
    }


    

}
