package aula8;
import java.util.ArrayList;
import java.util.List;

public class ementa {
    private String nome;
    private String local;
    private List<Prato> pratos;

    public ementa(String nome, String local) {
        this.nome = nome;
        this.local = local;
        this.pratos = new ArrayList<>();
    }

    public void addPrato(Prato p, DiaSemana d) {
        pratos.add(p);
    }

    @Override
    public String toString() {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (Prato p : pratos) {
            sb.append(p + ", dia " + DiaSemana.getDia(i) + "\n");
            i++;
        }
        return sb.toString();
    }

}
