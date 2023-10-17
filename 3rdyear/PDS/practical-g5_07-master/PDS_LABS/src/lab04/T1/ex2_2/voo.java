package lab04.T1.ex2_2;

public class voo {
    String codigo;
    aviao aviao;
    String[][] blank_a;
    int n_reservas = 1;
    int c = 0;
    String nreserva = "";

    public voo(String codigo, aviao aviao) {
        this.codigo = codigo;
        this.aviao =  aviao;
        this.blank_a = aviao.ArrayDisp();
    }
    

    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getNreserva() {
        return nreserva;
    }


    public void setNreserva(String nreserva) {
        this.nreserva = nreserva;
    }


    public void Disp(){
        int colunas = blank_a[0].length;
        System.out.printf(" ");
        for (int i = 1; i <= colunas; i++){
            System.out.printf("%3d", i);
        }
        System.out.printf("\n");
        int linhas = blank_a.length;
        for (int i = 0; i < linhas; i++){
            System.out.printf("%c", (char)(65 + i));
            for (int j = 0; j < colunas; j++){
                System.out.printf("%3s", blank_a[i][j]);
            }
            System.out.printf("\n");
        }
        
    }
    public void cancelar(int nreserva){
        int colunas = blank_a[0].length;
        int linhas = blank_a.length;
        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                if (blank_a[i][j].equals(String.format("%d", nreserva))){
                    blank_a[i][j] = "0";
                }
            }
        }
    }
    
    public void reserva(String tipo, int n){
        int colunas = blank_a[0].length;
        if (tipo.equals("E")){
            for (int i = 0; i < aviao.getC_executivas(); i++){
                boolean columnHasEmpty = true; 
                for (int j = 0; j < aviao.getL_executivas(); j++){
                    for (int k = 0; k < aviao.getL_executivas(); k++){
                        if (!blank_a[k][i].equals("0")){
                            columnHasEmpty = false; 
                            break;
                        }
                    }
                    if (!columnHasEmpty) { 
                        break; 
                    }
                }
                if (columnHasEmpty && aviao.getnexecutiva() >= n) {
                    for (int j = 0; j < aviao.getL_executivas(); j++){
                        blank_a[j][i] = String.format("%d", n_reservas);
                        n--;
                        aviao.setnexecutiva(aviao.getnexecutiva()-1);
                        //print the seat number and letter
                        String a = String.format("%4d", i+1) + String.format("%c", (char)(65 + j));
                        this.setNreserva(this.getNreserva() + a + "|");
                        if (n == 0){
                            n_reservas++;
                            return;
                        }
                    }
                }else if (aviao.getnexecutiva()<n){
                    System.out.println("Não há lugares disponiveis para a reserva: E "+ n);
                    return;
                }
            }
            for (int i = 0; i < aviao.getC_executivas(); i++){
                for (int j = 0; j < aviao.getL_executivas(); j++){
                    if (blank_a[j][i].equals("0") && aviao.getnexecutiva()>=n ){
                        blank_a[j][i] = String.format("%d", n_reservas);
                        n--;
                        aviao.setnexecutiva(aviao.getnexecutiva()-1);
                        String a = String.format("%d", i+1) + String.format("%c", (char)(65 + j));
                        this.setNreserva(this.getNreserva() + a + "|");
                        if (n == 0){
                            n_reservas++;
                            return;
                        }
                    }else if (aviao.getnexecutiva()<n){
                        System.out.println("Não há lugares disponiveis para a reserva: E "+ n);
                        return;
                    }
                    
                }
            }
        }
        else if (tipo.equals("T")){
            for (int i = aviao.getC_executivas(); i < colunas; i++){
                boolean columnHasEmpty = true; 
                for (int j = 0; j < aviao.getL_turisticas(); j++){
                    for (int k = 0; k < aviao.getL_turisticas(); k++){
                        if (!blank_a[k][i].equals("0")){
                            columnHasEmpty = false; 
                            break;
                        }
                    }
                    if (!columnHasEmpty) { 
                        break; 
                    }
                }
                if (columnHasEmpty && aviao.getnturistica()>=n) {
                    for (int j = 0; j < aviao.getL_turisticas(); j++){
                        blank_a[j][i] = String.format("%d", n_reservas);
                        n--;
                        aviao.setnturistica(aviao.getnturistica()-1);
                        String a = String.format("%d", i+1) + String.format("%c", (char)(65 + j));
                        this.setNreserva(this.getNreserva() + a + "|");
                        if (n == 0){
                            n_reservas++;
                            return;
                        }
                    }
                }else if (aviao.getnturistica()<n){
                    System.out.println("Não há lugares disponiveis para a reserva: T " + n);
                    return;
                }
            }
            for (int i = aviao.getC_executivas(); i < colunas; i++){
                for (int j = 0; j < aviao.getL_turisticas(); j++){
                    if (blank_a[j][i].equals("0") && aviao.getnturistica()>=n){
                        blank_a[j][i] = String.format("%d", n_reservas);
                        n--;
                        aviao.setnturistica(aviao.getnturistica()-1);
                        String a = String.format("%d", i+1) + String.format("%c", (char)(65 + j));
                        this.setNreserva(this.getNreserva() + a + "|");
                        if (n == 0){
                            n_reservas++;
                            return;
                        }
                    }
                    else if (aviao.getnturistica()<n){
                        System.out.println("Não há lugares disponiveis para a reserva T " + n);
                        return;
                    }
                }
            }
        }

    }
    

}
