package lab02.T2;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class wordAdder {
    public void addToSopa(String sopa, File file){
        String line = file.nextLine();
        int length = line.length();
        if (lineSize(line)){
            System.out.println("Error: cannot be grater than 40");
            file.close();
            return;
        }
        else{
            sopa=String[line.length()];
        }
        for(int i=0; i<length; i++) {
                if ((upperCase(line))){
                    sopa[i] = line;
                }else{
                    System.out.println("Error: has to be uppercase");
                    file.close();
                    return;
                }
            line = file.nextLine();
        }
    }
}
