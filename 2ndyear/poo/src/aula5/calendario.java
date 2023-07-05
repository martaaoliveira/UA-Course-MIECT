package aula5;

public class calendario {
    private int ano, dia_semana;
    public static String[] nomes_mes = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};


    public calendario(int ano, int dia_semana){
        this.ano=ano;
        this.dia_semana= dia_semana;
    }

    public int getano(){
        return this.ano;
    }
    public int dia(){
        return this.dia_semana;
    }
    public String mes(int mes){
        int totalDias=0;
        for (int i = 1; i < mes; i++) {
			totalDias += Data.DiasDoMes(i, this.ano);
		}
		return fazerCalendario(mes, (totalDias+this.dia_semana-1)%7+1);

    }

    public String toString() {
		String s = "";
		for (int i = 1; i <= 12; i++) {
			s += mes(i);
		}
		return s;
	}

    
    public String fazerCalendario(int mes,int dia_semana){
        String [] diasDasemana={"Su","Mo", "Tu", "We","Th","Fr","Sa"};

        switch(dia_semana){
            case 1:
            dia_semana=1;
            break;
            case 2:
            dia_semana=2;
            break;
            case 3:
            dia_semana=3;
            break;
            case 4:
            dia_semana=4;
            break;
            case 5:
            dia_semana=5;
            break;
            case 6:
            dia_semana=6;
            break;
            case 7:
            dia_semana=7;
            break;

        }
        
        System.out.printf("     %s %d\n",mes,ano);
        for(int i=0;i<diasDasemana.length;i++){
            System.out.printf("%s ",diasDasemana[i]);
        }
        System.out.println("");
        for(int i=0;i<7;i++){
            for(int k=1;k<8;k++){
                if( (i==0 && k<dia_semana) || ((i*7 + (k-dia_semana + 1)) > Data.DiasDoMes(mes, this.ano))){
                    System.out.printf("%3s", " ");	
                }
                else {
                    
                    System.out.printf("%3d", (i*7 + (k-dia_semana + 1)));
                }
            }
            System.out.println();
 

    }
    return "";
}
}
