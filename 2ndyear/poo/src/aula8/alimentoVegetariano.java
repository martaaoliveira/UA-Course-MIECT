package aula8;

public abstract class alimentoVegetariano extends alimento {

    public alimentoVegetariano(double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
    }


    public String toString() {
        return "AlimentoVegetariano ";
    }
    
}
