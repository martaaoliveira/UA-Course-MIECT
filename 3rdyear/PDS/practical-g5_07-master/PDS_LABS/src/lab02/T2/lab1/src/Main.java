package lab02.T2.lab1.src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main{
    static int maximo = 40;
    static String[][] matrix;
    static ArrayList<String> words = new ArrayList<String>();

    //funcao para ler a matriz(n x n)
    static boolean readMatrix(Scanner sc) {
        String[] firstLine = sc.nextLine().split(""); //meter as coisas para um array
        matrix = new String[firstLine.length][firstLine.length];
        if(firstLine.length < 1 || firstLine.length > maximo) {//condição do tamanho
            return false;
        }

        matrix[0] = firstLine;
        String[] line;
        for ( int i = 1; i< firstLine.length; i++) {
            line = sc.nextLine().split("");
            if ( !UpperCase(line) || line.length != firstLine.length || !Alpha(line)) {

                return false;
            }
            matrix[i] = line;

        }
        return true;
    }

    //funcao para verificar se existem maiusculas
    public static boolean UpperCase(String[] line) {
        for (String letra:line) {
            if ( !Character.isUpperCase(letra.charAt(0)) ) {
                return false;
            }
        }
        return true;

    }

    //se para verificar se é letra
    public static boolean Alpha(String[] line) {
        for (String letra : line) {
            if (Character.isDigit(letra.charAt(0)) ) {
                return false;
            }
        }
        return true;

    }

    //funcao para ler as palavras

    public static boolean readWords(Scanner sc) {
        while(sc.hasNextLine()) {
            //usar regex
            String[] word = sc.nextLine().split("[,; ]");
            words.addAll(List.of(word)); //passar de arrays com ponteiros para lista

        }

        return true;
    }

    //funcao para imprimir a sopa e letras



    public static void main(String[] args) {
        //ler o ficheiro
        try {
            File sopa= new File(args[0]);
            Scanner sc = new Scanner(sopa);
            //while (sc.hasNextLine()) {
            //String data = sc.nextLine();
            //}

            readMatrix(sc);
            readWords(sc);

            //chamar a class para resolver sopa


            // parte pra imprimir a tabela

            HashMap<String, List<Solver>> solutions = new HashMap<>();

            for(String wrd : words) {
                for(int x = 0; x < matrix.length; x++) {
                    for(int y = 0; y < matrix.length; y++) {
                        if(matrix[x][y].charAt(0) == Character.toUpperCase(wrd.charAt(0))) {
                            List <Solver> S = WSSolver.Solve(x, y, matrix, wrd, 1,Directions.NONE, x , y);
                            if (S.size() > 0) {
                                solutions.put(wrd, S);
                                System.out.printf("%-20s %-8d",wrd, wrd.length());
                                System.out.println(S.get(0));

                            }
                        }
                    }
                }
            }

            // parte para imprimir a sopa de letras
            char [][] SolutionMap = new char[matrix.length][matrix.length];
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix.length; j++) {
                    SolutionMap[i][j] = '.';
                }
            }

            for(String wrd : words) {

                //All the words in the list must be in the puzzle, just once
                if(solutions.get(wrd).size() != 1) {
                    System.out.print("palavra existe mais do que uma vez");
                    return;
                }

                Solver solucoes = solutions.get(wrd).get(0);
                int x = solucoes.getX();
                int y = solucoes.getY();
                int incX = 0;
                int incY = 0;
                Directions direction = solucoes.getDirection();

                switch (direction) {
                    case VERTICAL_UP: incY = -1;
                    case DIAGONAL_UP_RIGHT : { incX = 1; incY = -1; }
                    case HORIZONTAL_RIGHT : incX = 1;
                    case DIAGONAL_DOWN_RIGHT : { incX = 1; incY = 1; }
                    case VERTICAL_DOWN : incY = 1;
                    case DIAGONAL_DOWN_LEFT : { incX = -1; incY = 1; }
                    case HORIZONTAL_LEFT : incX = -1;
                    case DIAGONAL_UP_LEFT :{ incX = -1; incY = -1; }
                    default : throw new IllegalArgumentException("Unexpected value: " + direction);

                }

                // for (int i = 0; i < wrd.length(); i++) {
                //     SolutionMap[x - 1][y - 1] = wrd.charAt(i);
                //     x += incX;
                //     y += incY;
                // }
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length ; j++) {
                    System.out.print(SolutionMap[i][j] + " ");
                }
                System.out.println();
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}