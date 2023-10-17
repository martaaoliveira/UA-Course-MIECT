package lab02.T2;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class verify {
    public boolean lineSize(String s) {
        return (s.length() <= 40);
    }

    public boolean alphabet(char c) {
        return (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z');
    }
    public boolean upperCase(String s) {
        return s.equals(s.toUpperCase());
    }

    public boolean separator(char c) {
        return (c == ' ' || c == ',' || c == ';');
    }

    public boolean hasMinimumLength(String s) {
        return s.length() >= 3;
    }

    public boolean isSquare(char[][] sopa) {
        int length = sopa.length;
        for (int i = 0; i < length; i++) {
            if (sopa[i].length != length) {
                return false;
            }
        }
        return true;
    }

    public int wordInList(String s, String[] sopa) {
        int count = 0;
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length(); j++) {
                if (sopa[i].charAt(j) == s.charAt(j)) {
                    if (sopa[j].substring(s.length()).startsWith(s)) {
                        return count++;
                    }
                }
            }
        }
        return count;
    }
        
}
