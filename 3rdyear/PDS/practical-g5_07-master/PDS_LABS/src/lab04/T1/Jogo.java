package lab04.T1;
public class Jogo implements JGaloInterface{
    private char[][] jogo;
    private char actualPlayer;
    private int jogadas;

    
    public Jogo(){
        jogo = new char[3][3];
        int k = 0;
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                jogo[i][j] = (char) k;
                k++;
        actualPlayer = 'O';
    }
    
    public char getActualPlayer(){
        return actualPlayer;
    }
    
    public boolean setJogada(int lin, int col){
        if (jogo[lin-1][col-1] == 'O' || jogo[lin-1][col-1] == 'X')
            return false;
        else{
            jogadas++;
            jogo[lin-1][col-1] = actualPlayer;
            if (actualPlayer == 'O')
                actualPlayer = 'X';
            else
                actualPlayer = 'O';
            return true;
        }
    }
    
    public boolean isFinished(){
        if (checkResult() == 'O' || checkResult() == 'X' || jogadas == 9)
            return true;
        else
            return false;
    }
    
    public char checkResult(){
        for (int i=0; i<3; i++){
            if (jogo[i][0] == jogo[i][1] && jogo[i][1] == jogo[i][2])
                return jogo[i][0];
            if (jogo[0][i] == jogo[1][i] && jogo[1][i] == jogo[2][i])
                return jogo[0][i];
        }
        if (jogo[0][0] == jogo[1][1] && jogo[1][1] == jogo[2][2])
            return jogo[0][0];
        if (jogo[0][2] == jogo[1][1] && jogo[1][1] == jogo[2][0])
            return jogo[0][2];
        return ' ';
    }
    
}
