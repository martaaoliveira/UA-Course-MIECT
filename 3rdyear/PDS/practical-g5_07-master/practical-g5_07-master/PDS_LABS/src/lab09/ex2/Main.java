package lab09.ex2;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main (String[] args) {
        //implement here the code for testing the classes DesertChef,SushiChef ..
        List<String> requests = new ArrayList<>();
        requests.add("Veggie Burger");
        requests.add("Pasta Carbonara");
        requests.add("PLAIN Pizza, no toppings");
        requests.add("Sushi nigiri and sashimi");
        requests.add("Salad with Tuna");
        requests.add("Strawberry ice cream and waffles dessert");

        Handler handler = new SushiChef().setSucessor(
            new PastaChef().setSucessor(
                new BurgerChef().setSucessor(
                    new PizzaChef().setSucessor(
                        new DesertChef()))));
        for (String chef : requests) {
            System.out.println("Customer: I would like to order "+chef);
            handler.handleRequest(chef);
            System.out.println("\n");
        }  
    }

}

