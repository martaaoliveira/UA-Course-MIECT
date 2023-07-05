package aula7;

public class carro {
    private char classe; 
	private String combustivel;	
	private boolean disponivel;

    public carro(char classe, String combustivel) {
        if (classe >= 'A' && classe <= 'F' || classe >= 'a' && classe <= 'f' ){
		this.classe = classe;
		this.combustivel = combustivel;
        this.disponivel=true;
        }
	}

    	
	public boolean levantar() {
		if(disponivel) {
			this.disponivel=false;
            return true;
		}else {
			System.out.println("Carro alugado!");
            return false;
		}
	}

    public boolean entregar() {
		if(!disponivel) {
            this.disponivel=true;
            return true;
            
		}else {
            System.out.println(("Carro não  se encontra alugado!"));
			return false;
		}
	}

    @Override
    public String toString() {
        return "Carro de classe=" + classe + ", e combustivel=" + combustivel + "";
    }

	public String getCombustivel() {
		return combustivel;
	}
  

    public char getClasse() {
		return classe;
	}
}
