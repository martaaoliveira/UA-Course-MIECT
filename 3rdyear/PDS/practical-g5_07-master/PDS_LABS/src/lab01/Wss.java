package lab01;
import java.util.*;
import java.util.List;
import java.io.*;
public class Wss {

	public static void main(String[] args) throws IOException {
		
		try {
			if (args.length != 2) {
				System.out.println("Usage: java Wss lab01/<inputFile> lab01/<outputFile>");
				return;
			}
		} catch (Exception e) {
			System.exit(1);
		}
		

        String file = args[0];
		String output = args[1];
		File fileOutput = new File(output); // output file
		PrintStream stream = new PrintStream(fileOutput);
		System.setOut(stream);
		

		Sopa sopa = new Sopa(file);
		List<String> words = sopa.getWords();
		ArrayList<Word> found_words = new ArrayList<Word>();
		

		//Arrays that contain matrix lines of each direction
		List<List<String>> directions = new ArrayList<>();
		directions.add(sopa.getHorizontals());
		directions.add(sopa.getVerticals());
		directions.add(Sopa.reverse(sopa.getHorizontals()));
		directions.add(Sopa.reverse(sopa.getVerticals()));
		directions.add(sopa.getDiagonal(true));
		directions.add(sopa.getDiagonal(false));
		directions.add(Sopa.reverse(sopa.getDiagonal(false)));
		directions.add(Sopa.reverse(sopa.getDiagonal(true)));
	
		for (String word : words) {
			for (int i = 0; i < directions.size(); i++) {
				List<String> lines = directions.get(i);
				for (int j = 0; j < lines.size(); j++) {
					String line = lines.get(j);
					if (line.contains(word)) {
						int row, col;
						String direction;
						if (i == 0) {  // horizontal
							row = j + 1;
							col = line.indexOf(word) + 1;
							direction = "right";
							found_words.add(new Word(word,word.length(),row,col,direction));
							if(!isPalindrome(word)) continue;
						} else if (i == 1) {  // vertical
							row = line.indexOf(word) + 1;
							col = j + 1;
							direction = "down";
							found_words.add(new Word(word,word.length(),row,col,direction));
							if(!isPalindrome(word)) continue;
						} else if (i == 2) {  // horizontal invertido
							row = j + 1;
							col = sopa.getSize() - line.lastIndexOf(word);
							direction = "left";
							found_words.add(new Word(word,word.length(),row,col,direction));
							if(!isPalindrome(word)) continue;
						} else if (i == 3) {  // vertical invertido
							row = sopa.getSize() - line.lastIndexOf(word);
							col = j + 1;
							direction = "up";
							found_words.add(new Word(word,word.length(),row,col,direction));
							if(!isPalindrome(word)) continue;
						} else if (i == 4) {  // diagonal direita baixo
							int idx = j + 1;
							int drow = idx > sopa.getSize() ? idx - sopa.getSize() + 1 : 1;
							int dcol = idx > sopa.getSize() ? 1 : sopa.getSize()-idx+1;;
							row = drow + line.indexOf(word);
							col = dcol + line.indexOf(word);
							direction = "downright";
							found_words.add(new Word(word,word.length(),row,col,direction));
							if(!isPalindrome(word)) continue;
						} else if (i == 5) {  // diagonal esquerda baixo
							int idx = j + 1;
							int drow = idx > sopa.getSize() ? idx - sopa.getSize() + 1 : 1;
							int dcol = idx > sopa.getSize() ? sopa.getSize() : idx;
							row = drow + line.indexOf(word);
							col = dcol - line.indexOf(word);
							direction = "downleft";
							found_words.add(new Word(word,word.length(),row,col,direction));
							if(!isPalindrome(word)) continue;
						} else if (i == 6) {  // diagonal esquerda cima
							int idx = j + 1;
							int drow = idx > sopa.getSize() ? sopa.getSize() : idx;
							int dcol = idx > sopa.getSize() ? 2 * sopa.getSize() - idx : sopa.getSize();
							row = drow - line.indexOf(word);
							col = dcol - line.indexOf(word);
							direction = "upleft";
							found_words.add(new Word(word,word.length(),row,col,direction));
							if(!isPalindrome(word)) continue;

                        } else if (i == 7) {  // diagonal direita cima
							int idx = j + 1;
							int drow = idx > sopa.getSize()? sopa.getSize() : idx;
							int dcol = idx > sopa.getSize()? idx-sopa.getSize()+1 : 1;
							row = drow - line.indexOf(word);
							col = dcol + line.indexOf(word);
							direction = "upright";
							found_words.add(new Word(word,word.length(),row,col,direction));
							if(!isPalindrome(word)) continue;
							
						}
					}
				}
			}
		}

		//imprimir resultados 
		for (Word w : found_words) {
			System.out.println(w);
		}

	//imprimir matriz com palavras encontradas
		char[][] matrix = sopa.getMatrix();
		int rows = matrix.length;
		int cols = matrix[0].length;
		for (int i = 0; i <=rows; i++) {
			for (int j = 0; j <= cols; j++) {
				boolean found = false;
				for (Word palavra : found_words) {
					int startRow = palavra.getRow();
					int startCol = palavra.getCol();
					int endRow = startRow;
					int endCol = startCol;
					//System.out.println();
					//System.out.println(word);
					switch (palavra.getDirection()) {
						case "right":
                        endCol += palavra.getLength() - 1;
                        if (i == startRow && j >= startCol && j <= endCol) {
                            int pos = j - startCol;
                            System.out.print(palavra.getText().charAt(pos));
                            found = true;
                        }
                        break;
                    case "down":
                        endRow += palavra.getLength() - 1;
                        if (j == startCol && i >= startRow && i <= endRow) {
                            int pos = i - startRow;
                            System.out.print(palavra.getText().charAt(pos));
                            found = true;
                        }
                        break;
                    case "left":
                        endCol -= palavra.getLength() - 1;
                        if (i == startRow && j >= endCol && j <= startCol) {
                            int pos = startCol - j;
                            System.out.print(palavra.getText().charAt(pos));
                            found = true;
                        }
                        break;
                    case "up":
                        endRow -= palavra.getLength() - 1;
                        if (j == startCol && i >= endRow && i <= startRow) {
                            int pos = startRow - i;
                            System.out.print(palavra.getText().charAt(pos));
                            found = true;
                        }
                        break;
                    case "downright":
                        endRow += palavra.getLength() - 1;
                        endCol += palavra.getLength() - 1;
                        if (i <= endRow && j <= endCol && i >= startRow && j >= startCol
                                && (i - startRow == j - startCol)) {
                            int pos = i - startRow;
                            System.out.print(palavra.getText().charAt(pos));
                            found = true;
                        }
                        break;
                    case "downleft":
                        endRow += palavra.getLength() - 1;
                        endCol -= palavra.getLength() - 1;
                        if (i <= endRow && j >= endCol && i >= startRow && j <= startCol
                                && (i - startRow == startCol - j)) {
                            int pos = i - startRow;
                            System.out.print(palavra.getText().charAt(pos));
                            found = true;
                        }
                        break;
                    case "upleft":
                        endRow -= palavra.getLength() - 1;
                        endCol -= palavra.getLength() - 1;
                        if (i >= endRow && j >= endCol && i <= startRow && j <= startCol
                                && (startRow - i == startCol - j)) {
                            int pos = startRow - i;
                            System.out.print(palavra.getText().charAt(pos));
							found=true;
								}
								break;
					case "upright":
					endRow -= palavra.getLength() - 1;
					endCol += palavra.getLength() - 1;
					if (i <= startRow && i >= endRow && j >= startCol && j <= endCol && i - startRow == startCol - j) {
						int pos = startRow - i;
						System.out.print(palavra.getText().charAt(pos));
						found = true;
					}
					break;
					}
					if (found) {
						break;
					}
				}
				if (!found) {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
	
	}
	

	public static boolean isPalindrome(String word){
		return word.equals(new StringBuilder(word).reverse().toString());
	}





}