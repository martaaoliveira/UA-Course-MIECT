package aula6;
import java.time.OffsetDateTime;
public class Data {
    public static int[] diasMesComum = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int dia,mes,ano;

    public Data(int dia,int mes,int ano){
        if(valido(dia,mes,ano)==true){
        this.dia=dia;
        this.mes=mes;
        this.ano=ano;
        }
        else{
            System.out.println("Data invalida");
        }
    }
    
    public int compareTo(Data b) {
        int c = ano - b.getAno();
        if(c != 0){
            return c;
        }
        c = mes - b.getMes();
        if(c != 0){
            return c;
        }
        return dia - b.getDia();
    }

  

    public static boolean bissexto(int ano){
        return ((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0);
    }

    public static Data today(){
        OffsetDateTime dt = OffsetDateTime.now();
		int d = dt.getDayOfMonth();
		int m = dt.getMonthValue();
		int a = dt.getYear();
		return new Data(d, m, a);
    }

    public static int DiasDoMes(int mes, int ano){
        int nrdias;
        if (bissexto(ano)){
            if(mes == 2){
                return nrdias = 29;
            }else{
            nrdias = diasMesComum[mes-1];
            }
        }else{
            nrdias =diasMesComum[mes-1]; 
        }
        return nrdias;
    }

    public static boolean valido(int dia,int mes,int ano){
        if(mes<1|| mes>12)return false;
        int dias=DiasDoMes(mes,ano);
        if(dia<1 || dia>dias)return false;
        if(ano<0)return false;
        return true;

    }
    //quando é aplicado ao objeto nao se aplica static
    public void incrementar(int dia){
        while(dia>0){
            if(this.dia==DiasDoMes(this.mes, this.ano)){
                this.dia=1;
            
                if(this.mes==12){
                    this.mes=1;
                }
                else{
                    this.mes++;
                }
            }

            else{
                this.dia++;
            }

        
        dia--;
        }

    }

    public void decrementar(int dia){
        while(dia>0){
            if(this.dia==1){
                if(this.mes==1){
                    this.mes=12;
                    this.ano--;
                }
                else{
                    this.mes--;
                    this.dia=DiasDoMes(this.mes,this.ano);
                }
            }
           
            else{
                this.dia--;
            }

            dia--;
        }
    }

    public int getAno(){
        return this.ano;
    }
    public int getDia(){
        return this.dia;
    }
    public int getMes(){
        return this.mes;
    }

    public String toString() {
		return String.format("%04d-%02d-%02d", this.ano, this.mes, this.dia);
	}
    public int hashCode() {
        return dia + mes * 31 + ano * 366;
    }


    public boolean equals(Data b) {
        if (this == b){
            return true;
        }
        if (b == null){
            return false;
        }
        if (getClass() != b.getClass()){
            return false;
        }
        if (!(b instanceof Data)){
            return false;
        }
        if (ano != b.ano){
            return false;
        }
        if (dia != b.dia){
            return false;
        }
        if (mes != b.mes){
            return false;
        }
        return true;
    }
}

