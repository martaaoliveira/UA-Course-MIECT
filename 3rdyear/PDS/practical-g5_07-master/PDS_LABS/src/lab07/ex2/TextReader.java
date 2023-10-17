package lab07.ex2;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class TextReader implements ReaderInterface {

    private String filename;
    private List<String> data = new ArrayList<String>() ;

    public TextReader(String filename){
        this.filename=filename;
        readFile();
    }
    private void readFile() {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext(){
        return true;
    }
    @Override
    public String next(){
        return data.get(0);
    }
}
