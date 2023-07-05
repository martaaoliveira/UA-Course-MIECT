package aula8;

public class PratoDieta extends Prato{
    private double maxCalorias;
    public PratoDieta(String nome, double maxCalorias) {
        super(nome);
        this.maxCalorias = maxCalorias;
    }
    public double getMaxCalorias() {
        return this.maxCalorias;
    }

    public void setMaxCalorias(double maxCalorias) {
        this.maxCalorias = maxCalorias;
    }

    @Override
    public String toString() {
        double sum = 0;
        for (alimento alimento : this.getComposicao()) {
            sum += alimento.getCalorias();
        }
        return super.toString() + String.format(" - Dieta (%4.2f Calorias)",sum);
    }

   
    public boolean addIngrediente(alimento a){
        double totalCalorias = 0;
        for (alimento al : this.getComposicao()) {
            totalCalorias += al.getCalorias();
        }
        if(maxCalorias >= totalCalorias + a.getCalorias()){
            this.getComposicao().add(a);
            return true;
        }
        return false;
    }
}
