package lab05.ex2;

public class main {

    public static void main(String[] args) {
        CakeMaster cakeMaster = new CakeMaster();

        CakeBuilder chocolate = new ChocolateCakeBuilder();
        cakeMaster.setCakeBuilder(chocolate);
        cakeMaster.createCake("Congratulations"); // 1 cake layer
        Cake cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        System.out.println();

        CakeBuilder sponge = new SpongeCakeBuilder();
        cakeMaster.setCakeBuilder(sponge);
        cakeMaster.createCake(Shape.Square, 2, "Well done"); // squared, 2 layers
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);
        
        System.out.println();

        CakeBuilder yogurt = new YogurtCakeBuilder();
        cakeMaster.setCakeBuilder(yogurt);
        cakeMaster.createCake(3, "The best"); // 3 cake layers
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: " + cake);

        System.out.println();

         // criar um cheesecake
         CakeBuilder cheesecake = new CheesecakeBuilder();
         cakeMaster.setCakeBuilder(cheesecake);
         cakeMaster.createCake(Shape.Square,2, "Happy birthday"); // 4 cake layers squared 
         cake = cakeMaster.getCake();
         System.out.println("Your cake is ready: " + cake);
        // you should add here other example(s) of CakeBuilder
        }
    
}
