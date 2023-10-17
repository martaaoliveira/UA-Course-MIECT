package lab01;

import java.util.*;
import java.io.*;

public class Wsg {
    public static void main(String[] args) throws IOException{

        String inputFile = null;
        int c = 0; // posição do caractere
        String output = args[5];
        File fileOutput = new File(output); // arquivo de saída
        PrintStream stream = new PrintStream(fileOutput);
        System.setOut(stream);

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-i")) {
                inputFile = args[i + 1];
                break;
            }
        }
    
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-s")) {
                c = Integer.parseInt(args[i + 1]);
                break;
            }
        }
        
        // Lê o arquivo de entrada 
        if (inputFile != null) {
            try (Scanner scanner = new Scanner(new File(inputFile))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.length() > c) {
                        System.out.println(line.substring(c));
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Arquivo não encontrado: " + inputFile);
            }
        }
        try {
            Scanner scf = new Scanner(new File(inputFile));
            ArrayList<String> wordsList = new ArrayList<String>();
            while (scf.hasNextLine()) {
                String line = scf.nextLine();
                String[] list = line.split("\\W+");
                for (int i = 0; i < list.length; i++) {
                    if (list[i].length() >= 3) {
                        wordsList.add(list[i].toUpperCase());       
                    }
                }

            }

            for(int i = 0; i<wordsList.size(); i++){
				for(int j = 0; j<wordsList.size(); j++){
					if(i!=j && wordsList.get(i).contains(wordsList.get(j))){
						System.err.println("Redundant words: "+wordsList.get(i)+", "+wordsList.get(j));
						System.exit(1);
					}
				}
			}

            System.out.println("Insert words: " + wordsList);

            char[][] soup = new char[c][c];

            int size = soup.length;
    
            Random random = new Random();

            for (String word : wordsList) {
                boolean isPlaced = false;
                while (!isPlaced) {
                    int startX = random.nextInt(size);
                    int startY = random.nextInt(size);
                    int dirX = random.nextInt(3) - 1;
                    int dirY = random.nextInt(3) - 1;
                    if (dirX == 0 && dirY == 0) {
                        continue;
                    }
                    boolean canPlace = true;
                    int currentX = startX;
                    int currentY = startY;
                    for (int i = 0; i < word.length(); i++) {
                        if (currentX < 0 || currentX >= size || currentY < 0 || currentY >= size ||
                                ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(soup[currentX][currentY])) && soup[currentX][currentY] != word.charAt(i))) {
                            canPlace = false;
                            break;
                        }
                        currentX += dirX;
                        currentY += dirY;
                    }
                    if (canPlace) {
                        currentX = startX;
                        currentY = startY;
                        for (int i = 0; i < word.length(); i++) {
                            soup[currentX][currentY] = word.charAt(i);
                            currentX += dirX;
                            currentY += dirY;
                        }
                        isPlaced = true;
                    }
                }
            }
                    for (int i = 0; i < soup.length; i++) {
                        for (int j = 0; j < soup.length; j++) {
                            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(soup[i][j]))) {
                                continue;
                            }
                            soup[i][j] = getRandomChar();
                        }
                    }

                    for (int i = 0; i < c; i++) {
                        for (int j = 0; j < c; j++) {
                            System.out.print(soup[i][j] + " ");
                        }
                        System.out.println();
                    }

                } catch (Exception e) {
                    System.out.printf("Exception occurred trying to read '%s'.", inputFile);
                    System.exit(1);
                    return;
                }
            }

    private static char getRandomChar() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        return alphabet.charAt(random.nextInt(alphabet.length()));
    }
}