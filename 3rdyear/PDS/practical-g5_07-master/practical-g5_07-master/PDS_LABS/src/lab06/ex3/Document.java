package lab06.ex3;

import java.util.*;

public class Document {
    private int id;
    private String filename;
    private String[] array;
    public Document(String filename){
        this.filename=filename;
        readDocument();
    }
    private void readDocument(){
        Scanner scan = new Scanner(filename);
        ArrayList<String> data = new ArrayList<String>() ;
        while(scan.hasNextLine()){
            data.add(scan.nextLine());
        }
        array = data.toArray(new String[]{});
    }
    public String[] getStringArray(){
        Scanner scan = new Scanner(filename);
        ArrayList<String> data = new ArrayList<String>() ;
        while(scan.hasNextLine()){
            data.add(scan.nextLine());
        }
        return data.toArray(new String[]{});
    }
    public String[] getArray(){
        return array;
    }
    public void setID(int id){
        this.id=id;
    }
    public int getID(){
        return id;
    }
}

