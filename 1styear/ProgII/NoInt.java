//Marta Oliveira nº mec 97613

class NoInt {
    int valor;
    NoInt proximo;

    NoInt(int v) {
        valor = v;
        proximo = null;
    }

    NoInt(int v, NoInt n) {
        valor = v;
        proximo = n;
    }

    public static void main(String[] args){
	NoInt primeiro;
	
	// ultimo da lista
	primeiro = new NoInt(12);
	//~ /* 
	primeiro = new NoInt(3, primeiro);
	primeiro = new NoInt(55,primeiro);
	//~ */
	// primeiro da lista 
	
	/* get valor nó 2
	System.out.println(primeiro.proximo.proximo.valor);
	*/
	/* 1 adicionar nós
	// nó (77) na posição 1
	primeiro.proximo = new NoInt(77,primeiro.proximo);
	// nó (73) na posição 2
	primeiro.proximo.proximo = new NoInt(73,primeiro.proximo.proximo);
	*/
	/* 2 apagar nó 1
	primeiro.proximo = primeiro.proximo.proximo;
	*/
    
	/* 3 get
	System.out.println(getValor(primeiro, 2));
	*/
	/* 4 add
	// adicionar nó (666) na posição 2
	primeiro = addNo(primeiro, 2, 666);
	*/
	
	// imprime lista
	System.out.println("Lista:");
	NoInt no = primeiro;
	while (no != null){
	    System.out.println(no.valor);
	    no = no.proximo;
	}	
	
	// imprime recursiva
	printLista(primeiro);System.out.println();
	System.out.println("[" + toString(primeiro) + "]");
    }
    // imprime lista - recursivamente
    static void printLista(NoInt n){
	if (n != null){
	    
	    System.out.print(n.valor+ " ");
	    printLista(n.proximo);	// e antes do print?
	} 
    }
    static String toString(NoInt n){
	if (n == null)return "";
	else return n.valor + " " + toString(n.proximo);
    }
    // get valor nó na posição p
    static int getValor(NoInt n, int p){
	if (p == 0) return n.valor;
	else return getValor(n.proximo, p-1);
    }
    
    // adiciona nó na posição p
    static NoInt addNo(NoInt n, int p, int v){
	if (p == 0)
	    return new NoInt(v,n);
	else {
	    n.proximo = addNo(n.proximo, p-1, v);
	    return n; 
	}
    }
    
    // métodos para devolver o comprimento da lista
    // versão iterativa
    static int lengthI(NoInt n){
     int count = 0;
     while (n!=null)
	 {
		 count ++;
		 n=n.proximo;
	 }
	 return count;
     }
      // versão recursiva
     static int lengthR(NoInt no){
     if(no == null){
		 return 0;
	 }
	 else
	 {
		 return 1 +lengthR(no.proximo);
	 }
     }
     
}
