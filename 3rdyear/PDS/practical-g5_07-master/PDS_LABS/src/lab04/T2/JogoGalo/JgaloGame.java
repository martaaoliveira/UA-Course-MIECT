
package lab04.T2.JogoGalo;

public class JgaloGame implements JGaloInterface{
    char ActualPlayer;
    char winner;
    char[][] JoguinhodoGalo=new char[3][3];
    int jogada=0;



    public JgaloGame(){
        this.ActualPlayer='X';

    }

    public JgaloGame(char ActualPlayer){
        this.ActualPlayer=ActualPlayer;

    }

    public char getActualPlayer(){
        return this.ActualPlayer;
    }

    public boolean setJogada(int row, int col){
        
        JoguinhodoGalo[row-1][col-1]=this.ActualPlayer;

        if(this.ActualPlayer=='X'){this.ActualPlayer='O';}
        else{this.ActualPlayer='X';}

        jogada++;
        return true;

    }

    public boolean isFinished(){
        boolean end;
        char jogador;

        //para ganhar tem de ter 3 em linha , coluna ou diagonal
        
        //linhas

        for(int x=0;x<3;x++){
            end=true;
            jogador=JoguinhodoGalo[x][0];
            for(int y=1;y<3;y++){
                if(JoguinhodoGalo[x][y]!=jogador || JoguinhodoGalo[x][y]==0){
                    end=false;
                    break;
                }
                
            }
            if(end==true){
                winner=jogador;
                return true;
            }  
        }

        //colunas

        for(int y=0;y<3;y++){
            end=true;
            jogador=JoguinhodoGalo[0][y];
            for(int x=1;x<3;x++){
                if(JoguinhodoGalo[x][y]!=jogador || JoguinhodoGalo[x][y]==0){
                    end=false;
                    break;
                }
                
            }
            if(end==true){
                winner=jogador;
                return true;
            }  
        }

        //diagonais
        //1

        end=true;
        jogador=JoguinhodoGalo[0][0];
        for(int y=1;y<3;y++){
            int x=y;
            if(JoguinhodoGalo[x][y]!=jogador || JoguinhodoGalo[x][y]==0){
                end=false;
                break;
            }
            
        }
        if(end==true){
            winner=jogador;
            return true;
        }  



        
        //2
        end=true;
        jogador=JoguinhodoGalo[0][2];
        for(int y=1;y>=0;y--){
            int x=2-y;
            if(JoguinhodoGalo[x][y]!=jogador || JoguinhodoGalo[x][y]==0){
                end=false;
                break;
            }
            
        }
        if(end==true){
            winner=jogador;
            return true;
        }


        //em caso de empate
        if(jogada==9){
            winner=' ';
            return true;
        }


        return false;



        


    }

    public char checkResult(){
        return winner;

    }



    
    
}

