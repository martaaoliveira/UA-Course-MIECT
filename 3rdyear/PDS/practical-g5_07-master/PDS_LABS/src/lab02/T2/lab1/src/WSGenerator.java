package lab02.T2.lab1.src;
import java.io.IOException;

import java.util.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class WSGenerator {
    private int Size;
    private List<String> words;
    private char[][] puzzle;
    boolean v=false ;


    public WSGenerator(int Size, List<String> words) {
        this.Size = Size;
        this.words = words;

        createTable();

        for (String word : words) {
            while (v==false) {
                v = verification(word);
            }
            v=false;
        }
        printTable();
    }

    private void createTable () {
        puzzle = new char [Size][Size];
        for (int linha = 0; linha < puzzle.length; linha++) {
            for (int coluna = 0; coluna < puzzle.length; coluna++) {
                puzzle[linha][coluna]='*'; // cria uma tabela só com *
            }
        }
    }

    private boolean verification(String word) {
        int x = ThreadLocalRandom.current().nextInt(1,19+1);
        int y = ThreadLocalRandom.current().nextInt(1,19+1);
        int op = ThreadLocalRandom.current().nextInt(1,8+1);

        if (puzzle[x][y]=='*') {
            switch(op) {
                case 1: //esquerda
                    if(y-word.length() >= 0) {
                        for (int i = 0;  i < word.length(); i++) {
                            if (puzzle[x][y-i]!='*') { //se ao tentar colocar em algum dos espaços ja estiver ocupado
                                return false;
                            }
                        }

                        for (int i = 0; i < word.length(); i++) {
                            puzzle[x][y-i]=word.charAt(i);

                        }
                    }
                    else
                        return false;

                    break;

                case 2: //direita
                    if(y+word.length() <= Size-1) {
                        for (int i = 0; i < word.length(); i++) {
                            if (puzzle[x][y+i]!='*') {
                                return false;
                            }
                        }

                        for (int i = 0; i < word.length(); i++) {
                            puzzle[x][y+i]=word.charAt(i);

                        }
                    }
                    else
                        return false;

                    break;

                case 3: //cima
                    if(x-word.length() >= 0) {
                        for (int i = 0; i < word.length(); i++) {
                            if (puzzle[x-i][y]!='*') {
                                return false;
                            }
                        }

                        for (int i = 0; i < word.length(); i++) {
                            puzzle[x-i][y]=word.charAt(i);


                        }
                    }
                    else
                        return false;

                    break;

                case 4: //baixo
                    if(x+word.length() <= Size-1) {
                        for (int i = 0; i < word.length(); i++) {
                            if (puzzle[x+i][y]!='*') {
                                return false;
                            }
                        }

                        for (int i = 0; i < word.length(); i++) {
                            puzzle[x+i][y]=word.charAt(i);

                        }

                    }
                    else
                        return false;

                    break;

                case 5: // / direita para cima
                    if(y+word.length() <= Size && x-word.length() >= 0 ) {
                        int k=0;
                        for (int i = 0; i < word.length(); i++) {
                            if (puzzle[x-i][y+k]!='*') {
                                return false;
                            }
                            k++;

                        }

                        int j=0;
                        for (int i = 0; i < word.length(); i++) {
                            puzzle[x-i][y+j]=word.charAt(i);
                            j++;
                        }
                    }

                    else
                        return false;

                    break;

                case 6:// / direita para baixo
                    if(y-word.length() >= 0 && x+word.length() <= Size ) {
                        int k=0;
                        for (int i = 0; i < word.length(); i++) {
                            if (puzzle[x+i][y-k]!='*') {
                                return false;
                            }
                            k++;

                        }

                        int j=0;
                        for (int i = 0; i < word.length(); i++) {
                            puzzle[x+i][y-j]=word.charAt(i);
                            j++;
                        }
                    }

                    else
                        return false;

                    break;

                case 7: // \ esquerda para cima
                    if(y-word.length() >= 0 && x-word.length() >= 0 ) {
                        int k=0;
                        for (int i = 0; i < word.length(); i++) {
                            if (puzzle[x-i][y-k]!='*') {
                                return false;
                            }
                            k++;

                        }

                        int j=0;
                        for (int i = 0; i < word.length(); i++) {
                            puzzle[x-i][y-j]=word.charAt(i);
                            j++;
                        }
                    }

                    else
                        return false;

                    break;

                case 8: // \ esquerda para baixo
                    if(y+word.length() <= Size && x+word.length() <= Size ) {
                        int k=0;
                        for (int i = 0; i < word.length(); i++) {
                            if (puzzle[x+i][y+k]!='*') {
                                return false;
                            }
                            k++;

                        }

                        int j=0;
                        for (int i = 0; i < word.length(); i++) {
                            puzzle[x+i][y+j]=word.charAt(i);
                            j++;
                        }
                    }

                    else
                        return false;

                    break;

                default:
                    return false;
            }
        }
        else {
            return false;
        }

        return true;
    }


    public void printTable () {
        Random r = new Random();
        //sopa de letras por resolver
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                if (puzzle[i][j]=='*') {
                    char c = (char)(r.nextInt(26) + 'a'); //coloca uma letra random onde nao tao as palavras
                    System.out.print(c);
                }
                else
                    System.out.print(puzzle[i][j]);
            }
            System.out.println();
        }
        //sopa de letras resolvida
        System.out.println();
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                System.out.print(puzzle[i][j]);
            }
            System.out.println();
        }
    }

    public boolean writeToFile(String name){

        try {
            FileWriter writer = new FileWriter("src/Guiao1/"+name);
            PrintWriter printWriter = new PrintWriter(writer);

            Random r = new Random();

            for (int i = 0; i < puzzle.length; i++) {
                for (int j = 0; j < puzzle.length; j++) {
                    if (puzzle[i][j]=='*') {
                        char c = (char) (r.nextInt(26) + 'a'); //coloca uma letra random onde nao tao as palavras
                        printWriter.print(c);
                    }
                    else{
                        printWriter.print(puzzle[i][j]);
                    }
                }printWriter.println();
            }

            printWriter.close();
            writer.close();

        } catch (IOException e) {
            return false;
        }


        return true;
    }
}
