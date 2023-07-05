package aula6;

public class conjunto {
    private int tamanho;
    private int[]vetor;

    public conjunto(){
        //this.tamanho=0;
        this.vetor=new int[0];
    }

    public int tamanho() {
        return vetor.length;
    }
    public int[] getVetor(){
        return vetor;
    }


    public void insert(int n){
        if(this.contains(n)) {return;}
        int newsize= vetor.length+1;
        int[] vetor2 = new int[newsize];
        for(int i = 0; i<vetor.length; i++){
            vetor2[i] = vetor[i];
        }
        vetor2[newsize-1] = n;
        vetor = vetor2;
    }

    public boolean contains(int n){
        for(int i=0; i < vetor.length; i++) {
            if( this.vetor[i] == n ) return true;
        }
        return false;
    }
    

    public void remove(int n) {
        //boolean removido=false;
        if(!this.contains(n)) {return;}
        int[] vetor2 = new int[vetor.length -1];
        int i;
        boolean removed=false;
        for(i = 0; i < vetor.length; i++) {
            if(vetor[i] == n ) {
                removed=true;
                continue;
            }
            if(!removed) {
				vetor2[i] = vetor[i];
			} else {
				vetor2[i-1] = vetor[i];
			}

        }
        vetor=vetor2;      
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < vetor.length; i++) {
            string += vetor[i] + " ";
        }
        return string;
    }

   
    public conjunto combine(conjunto add) {
        conjunto combinado = new conjunto();
        combinado = this;

        for (int i = 0; i < add.tamanho; i++) {
            int n=add.getVetor()[i];
            if(!combinado.contains(n)){
                combinado.insert(vetor[i]);
            }
        }
    
        return combinado;
    }

    public conjunto subtract(conjunto diff) {
        conjunto resultado = new conjunto();
        resultado = this;
        for (int i = 0; i < diff.tamanho(); i++) {
            int n = diff.getVetor()[i];
            if(resultado.contains(n)){
                resultado.remove(n);
            }
            
        }

        return resultado;
    }

    public conjunto intersect(conjunto iter) {
        conjunto resultado = new conjunto();
        for (int i = 0; i < iter.tamanho(); i++) {
            if(this.contains(iter.getVetor()[i])) resultado.insert(iter.getVetor()[i]);
        }
        return resultado;
    }

    public void empty() {
		this.vetor = new int[0];
	}

}
