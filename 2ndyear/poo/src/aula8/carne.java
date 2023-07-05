package aula8;

public class carne extends alimento {
    
    private VariedadeCarne variedade;
    
    public carne(VariedadeCarne variedade,double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.variedade = variedade;
    }

    public VariedadeCarne getVariedade() {
        return variedade;
    }

    public void setVariedade(VariedadeCarne variedade) {
        this.variedade = variedade;
    }


    @Override
    public String toString() {
        return "Carne =" + variedade;
    }

   
    public boolean equals(carne ca) {
        if (this == ca)
            return true;
        if (!super.equals(ca))
            return false;
        if (variedade != ca.variedade)
            return false;
        return true;
    }


}
