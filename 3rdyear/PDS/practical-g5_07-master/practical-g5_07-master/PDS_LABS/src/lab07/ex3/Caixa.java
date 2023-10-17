package lab07.ex3;
import java.util.ArrayList;
import java.util.List;
//using pattern composite 
public class Caixa extends Alimento {

    private String name;
    private List<Alimento> alimentos = new ArrayList<Alimento>();
    private double weight;
    private double totalWeight=0;

    public Caixa(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }

    public void add(Alimento al) {
        alimentos.add(al);
    }

    public void draw() {
        System.out.println(buffer.toString() + "* Caixa '" + name + "' [ Weight: " + weight + " Total Weight: " + (getTotalWeight() + weight) + "]");
        for (Alimento al : alimentos) {
            al.draw();
        }
    }

    private double getTotalWeight() {
        for (int i = 0; i < alimentos.size(); i++) 
            totalWeight += alimentos.get(i).getWeight();
        return totalWeight;
    }
}