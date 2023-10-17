package lab03.ex1;
import java.util.*;
public class JogoGalo implements JGaloInterface {
    
    private char[][] game;
	private char player;
	private char winner;
	private int nrmoves;

    public JogoGalo(){
        this.game= new char [3][3];
        this.player= 'X';
        this.winner=' ';
        this.nrmoves = 0;

    }

    public char getActualPlayer(){
        return this.player;
    }

    public boolean setJogada(int lin,int col){
        game[lin - 1][col - 1] = player;
        player = player == 'X' ? 'O' : 'X'; // alternar jogador
        nrmoves++;
        return true;
    }

    public boolean isFinished(){
        
        if(nrmoves==9){
            return true;
        }
    
        ArrayList<String>lines= getLines();

        for(String line : lines){
            if(line.equals("XXX")){
                winner='X';
                return true;
            }
            if(line.equals("OOO")){
                winner='O';
                return true;
            }
        }
        return false;
    }

    public char checkResult(){
        return winner;
    }

    private ArrayList<String> getLines(){
        ArrayList<String> sequence = new ArrayList<>();
        
        // Adicionar linhas e colunas
        for (int i = 0; i < game.length; i++) {
            StringBuilder row = new StringBuilder();
            StringBuilder col = new StringBuilder();
            for (int j = 0; j < game.length; j++) {
                row.append(game[i][j]);
                col.append(game[j][i]);
            }
            sequence.add(row.toString());
            sequence.add(col.toString());
        }

        // Adicionar diagonais
        StringBuilder diagonal1 = new StringBuilder();
        StringBuilder diagonal2 = new StringBuilder();
        for (int i = 0; i < game.length; i++) {
            diagonal1.append(game[i][i]);
            diagonal2.append(game[i][game.length - i - 1]);
        }
        sequence.add(diagonal1.toString());
        sequence.add(diagonal2.toString());

    return sequence;
    }


}
