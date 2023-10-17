package lab01;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
public class Sopa {
	private int size;
	private char[][] matrix;
	private ArrayList<String> words;

	public Sopa(String file) throws FileNotFoundException{
		
		try (Scanner sc = new Scanner(new File(file))) {
			String line = sc.nextLine();
			// number of columns and rows (matrix NxN)
			this.size = line.length();
			if (size > 40) {
				throw new IllegalArgumentException("Puzzle can't be bigger than 40x40");
			}
			this.matrix = new char[size][size];

			// fill matrix char by char
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					char c = line.charAt(col);
					if (!Character.isUpperCase(c)) {
						throw new IllegalArgumentException("Puzzle characters need to be uppercase letters");
					}
					matrix[row][col] = c;
				}
				if (row < size - 1) {
					line = sc.nextLine();
				}
			}

			this.words = new ArrayList<>();
			
			// words to be found
			Set<String> uniqueWords = new HashSet<>(); 
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				String[] list = line.split("\\W+");
				for (int i = 0; i < list.length; i++) {
					String word = list[i].toUpperCase();
					if (word.matches("^[A-Z]{4,}$") && uniqueWords.add(word)) {
						words.add(word);
					}
				}
			}
		}
	}

	public int getSize() {
		return size;
	}

	public char[][] getMatrix() {
		return matrix;
	}

	public List<String> getWords() {
		return words;
	}

	public List<String> getHorizontals() {
		List<String> horizontais = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			horizontais.add(new String(matrix[i]));
		}
		return horizontais;
	}

	public List<String> getVerticals() {
		List<String> verticais = new ArrayList<>();
		for (int row = 0; row < matrix.length; row++) {
			StringBuilder sb = new StringBuilder(size);
			for (int col = 0; col < matrix.length; col++) {
				sb.append(matrix[col][row]);
			}
			verticais.add(sb.toString());
		}
		return verticais;
	}


	
	public List<String> getDiagonal(boolean upRight) {
		ArrayList<String> diags = new ArrayList<String>();
		int rows = matrix.length;
		int cols = matrix[0].length;
	
		if (upRight) {
			// up-right direction
			for (int i = 0; i < rows; i++) {
				String s = "";
				for (int j = 0; j < cols; j++) {
					if (i + j < rows) {
						s += matrix[i + j][j];
					}
				}
				diags.add(s);
			}
			for (int j = 1; j < cols; j++) {
				String s = "";
				for (int i = 0; i < rows; i++) {
					if (i + j < cols) {
						s += matrix[i][i + j];
					}
				}
				diags.add(s);
			}
		} else {
			// up-left direction
			char[][] matrix_r = reverseLines();
			for (int i = 0; i < rows; i++) {
				String s = "";
				for (int j = 0; j < cols; j++) {
					if (i + j < rows) {
						s += matrix_r[i + j][j];
					}
				}
				diags.add(s);
			}
			for (int j = 1; j < cols; j++) {
				String s = "";
				for (int i = 0; i < rows; i++) {
					if (i + j < cols) {
						s += matrix_r[i][i + j];
					}
				}
				diags.add(s);
			}
		}
		return diags;
	}

	public char[][] reverseLines(){
		char[][] matrixreversed = new char[size][size];
		for(int row = 0; row<size; row++){
			String row_r = new StringBuilder(String.valueOf(matrix[row])).reverse().toString();
			matrixreversed[row] = row_r.toCharArray();
		}
		return matrixreversed;
	}
	
	public static List<String> reverse(List<String> lista) {
		List<String> reversed = new ArrayList<String>();
		for (String r : lista) {
			reversed.add(new StringBuilder(r).reverse().toString());
		}
		return reversed;
	}

}