package lab04.T1.ex2_2;
public class aviao {

    private int nturistica;
    private int nexecutiva;
    private int l_turisticas;
    private int l_executivas;
    private int c_turisticas;
    private int c_executivas;

    public aviao (String lexecutiva,String lturistica) {
        this.c_executivas = Integer.parseInt(lexecutiva.split("x")[0]);
        this.l_executivas = Integer.parseInt(lexecutiva.split("x")[1]);
        this.c_turisticas = Integer.parseInt(lturistica.split("x")[0]);
        this.l_turisticas = Integer.parseInt(lturistica.split("x")[1]);
        
        this.nturistica = Integer.parseInt(lturistica.split("x")[0]) * Integer.parseInt(lturistica.split("x")[1]);
        this.nexecutiva = Integer.parseInt(lexecutiva.split("x")[0]) * Integer.parseInt(lexecutiva.split("x")[1]);
    }

    public String[][] ArrayDisp(){
        int colunas = c_turisticas + c_executivas;
        int linhas = 0;
        if (l_executivas > l_turisticas){
            linhas = l_executivas;
        }
        else
            linhas = l_turisticas;
        String[][] array = new String[linhas][colunas];
        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                array[i][j] = "0";
            }
        }
        // make the unused array positions empty
        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                if (i >= l_executivas && j < c_executivas){
                    array[i][j] = "";
                }
            }
        }
        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                if (i >= l_turisticas && j >= c_executivas){
                    array[i][j] = "";
                }
            }
        }
        
        return array;
    }

    public int getL_turisticas() {
        return l_turisticas;
    }



    public int getL_executivas() {
        return l_executivas;
    }



    public int getC_turisticas() {
        return c_turisticas;
    }



    public int getC_executivas() {
        return c_executivas;
    }



    public int getnturistica() {
        return nturistica;
    }

    public void setnturistica(int lturistica) {
        this.nturistica = lturistica;
    }

    public int getnexecutiva() {
        return nexecutiva;
    }

    public void setnexecutiva(int lexecutiva) {
        this.nexecutiva = lexecutiva;
    }
}
