package lab05.ex1;

public class PagaLeva {

    public static void main (String[] args) {
        
        final int MENUS = 4;
        Portion[] menu = new Portion[MENUS];
        menu[0] = PortionFactory.createBeverage(Temperature.Cold);
        menu[1] = PortionFactory.createMeat(Temperature.Warm);
        menu[2] = PortionFactory.createBeverage(Temperature.Warm);
        menu[3] = PortionFactory.createMeat(Temperature.Cold);

        System.out.println("---- Thank you for choosing your meal! ----");
        for (Portion p : menu)
            System.out.println(p);

        ContainerFactory[] containers = new ContainerFactory[MENUS];
        for (int m = 0; m < MENUS; m++) {
            containers[m] = ContainerFactory.create(menu[m]);
        }

        System.out.println("---- Take the packages: ----");
        for (ContainerFactory c : containers) {
            System.out.println(c);
        }

    }
    
}
