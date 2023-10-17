package lab10.ex2;

public class EmployeeFactory {
    public static final String [] names = {"Mac", "Janela", "Linux"};

    public static Employee getCostumer(String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                return new Programmer(name);
            }
        }
        return new NullEmployee();
    }
}
