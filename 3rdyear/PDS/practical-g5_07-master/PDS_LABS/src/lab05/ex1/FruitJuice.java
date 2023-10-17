package lab05.ex1;

public class FruitJuice implements Portion 
{
    private Temperature temp = Temperature.Cold;
    private State state = State.Liquid;
    private String juiceName = "Orange";

    public Temperature getTemperature()
    {
        return this.temp;
    }

    public State getState()
    {
        return this.state;
    }

    @Override
    public String toString()
    {
        StringBuilder string_build = new StringBuilder();
        string_build.append("FruitJuice: ")
                    .append(juiceName).append(", ")
                    .append("Temperature ").append(this.temp).append(", ")
                    .append("State ").append(this.state);
        return string_build.toString();
    }
}
