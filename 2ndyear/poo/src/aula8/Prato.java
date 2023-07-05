package aula8;
import java.util.ArrayList;
import java.util.List;
public class Prato implements Comparable<Prato> {
    private String nome;
    private List<alimento> composicao;

    public Prato(String nome) {
        this.nome = nome;
        composicao = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Prato '%s', composto por %d Ingredientes", nome, composicao.size());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<alimento> getComposicao() {
        return composicao;
    }

    public void setComposicao(List<alimento> composicao) {
        this.composicao = composicao;
    }
    @Override
    public int compareTo(Prato p2) {
        double caloriasPrato1 = 0;
        double caloriasPrato2 = 0;
        for (alimento alimento : composicao) {
            caloriasPrato1 += alimento.getCalorias();
        }
       
        for (alimento alimento : p2.getComposicao()) {
            caloriasPrato2 += alimento.getCalorias();
        }

        if (caloriasPrato1 - caloriasPrato2 >= 0) {
            return 1;
        } else return 0;
    }

    public boolean addIngrediente(alimento a) {
        composicao.add(a);
        return true;
    }


}
