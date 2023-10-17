package lab02.T2;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) {
        //2D char array
        String[] sopa;
        String[] word;
        String tempString = "";
        int length = 0, wordIndex = 0;
        try {
            File file = new File(args[0]);
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            length = line.length();
            if (length > 40){
                System.out.println("Error: cannot be grater than 40");
                sc.close();
                return;
            }
            for(int i=0; i<length; i++) {
                    for(int j=0; j<length; j++){
                        if (upperCase(line)){
                            sopa[i] = line;
                        }else{
                            System.out.println("Error: cannot be a number");
                            sc.close();
                            return;
                        }
                    }
                line = sc.nextLine();
            }
            for(int i=0; i<line.length(); i++) {
                //if the caracter is between a-z or A-Z
                if ((line.charAt(i) >= 'a' && line.charAt(i) <= 'z') || (line.charAt(i) >= 'A' && line.charAt(i) <= 'Z')){
                    tempString += line.charAt(i);
                }else if (line.charAt(i) == ' ' || line.charAt(i) == ',' || line.charAt(i) == ';'){
                    if (tempString.equals(tempString.toUpperCase())){
                        System.out.println("Error: cannot be all uppercase");
                        sc.close();
                        return;
                    }
                    word[wordIndex] = tempString;
                    tempString = "";
                    wordIndex++;
                }
                System.out.println(tempString);
            }
            //while there is a next line
            while (sc.hasNextLine()) {
                tempString = "";
                line = sc.nextLine();
                for(int i=0; i<line.length(); i++) {
                    //if the caracter is between a-z or A-Z
                    if ((line.charAt(i) >= 'a' && line.charAt(i) <= 'z') || (line.charAt(i) >= 'A' && line.charAt(i) <= 'Z')){
                        tempString += line.charAt(i);
                    }else if (line.charAt(i) == ' ' || line.charAt(i) == ',' || line.charAt(i) == ';'){
                        word[wordIndex] = tempString;
                        tempString = "";
                        wordIndex++;
                    }
                    System.out.println(tempString);
                }
            }
            sc.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for(int i=0; i<wordIndex; i++){
            System.out.println(word[i]);
        }

    }
}