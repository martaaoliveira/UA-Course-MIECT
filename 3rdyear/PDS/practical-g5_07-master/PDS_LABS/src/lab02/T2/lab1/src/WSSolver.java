package lab02.T2.lab1.src;
import java.util.ArrayList;
import java.util.List;

public class WSSolver {
    public static Directions Direcao(int x, int y, int Px, int Py) {
        int difx, dify;

        //diferença entre as cordenadas dos pontos onde x e y sao as coordenadas do centro
        difx = Px - x;
        dify = Py - y;

        //todas as direções possiveis
        if(difx > 0 && dify == 0) {
            return Directions.HORIZONTAL_RIGHT;
        }

        if(difx < 0 && dify == 0) {
            return Directions.HORIZONTAL_LEFT;
        }

        if(difx == 0 && dify > 0) {
            return Directions.VERTICAL_DOWN;
        }

        if(difx == 0 && dify < 0) {
            return Directions.VERTICAL_UP;
        }

        if(difx < 0 && dify > 0) {
            return Directions.DIAGONAL_DOWN_LEFT;
        }

        if(difx > 0 && dify > 0) {
            return Directions.DIAGONAL_DOWN_RIGHT;
        }

        if(difx < 0 && dify < 0) {
            return Directions.DIAGONAL_UP_LEFT;
        }

        if(difx > 0 && dify < 0) {
            return Directions.DIAGONAL_UP_RIGHT;
        }


        return Directions.NONE;
    }

    // x e y sao os pontos do centro
    public static List<Solver> Solve(int x,int y,String[][] matrix,String word,int index,Directions Direcao, int ox, int oy){

        //caso base
        if(index == word.length()) {
            return List.of(new Solver(ox + 1,oy + 1,Direcao));
        }

        ArrayList<Solver> solucoes = new ArrayList<Solver>();

        for(int k1 = -1; k1 < 2; k1++) {//para o x
            for(int k2 = -1; k2 < 2; k2++) {//para o y
                if(k1 == 0 && k2 == 0) {
                    continue;
                }
                int Px = x + k1;
                int Py = y + k2;

                Directions a = Direcao(x,y,Px,Py);

                if((Px >= 0 && Px < matrix.length) && (Py >= 0 && Py < matrix.length) && matrix[Px][Py].charAt(0) == Character.toUpperCase(word.charAt(index)) && (a == Direcao || Direcao == Directions.NONE)) { //meter as duas para maiusculas
                    solucoes.addAll(Solve(Px,Py,matrix,word,index + 1,a,ox,oy));
                }
            }
        }
        return solucoes;
    }
}
