package aula8;

public class PratoVegetariano extends Prato {
    public PratoVegetariano(String nome) {
    super(nome);
    }

    @Override
    public String toString() {
        return super.toString() + " Prato Vegetariano";
    }

    public boolean addIngrediente(alimento a) {
        if (a instanceof alimentoVegetariano) {
            this.getComposicao().add(a);
            return true;
        }
        return false;
    }

}
