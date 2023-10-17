package lab06.ex2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsTxt implements ContactsStorageInterface{
    
    private String fileName;

    public ContactsTxt(String fileName) {
        this.fileName = fileName;
    }
        
    public boolean saveContacts(List<Contact> list) {
        String data="";
        for(Contact c: list){
            data+=c.getName()+ "\t"+ c.getPhone() +"\n";
        
        try {
            Path filePath = Paths.get(this.fileName);
            FileWriter writer = new FileWriter(filePath.toString());
            writer.write(data);
            writer.close();

        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
        
        }
        
        return true;
    }
    
       
    public List<Contact> loadContacts() {
        
        List<Contact> list = new ArrayList<>();
        try {
            Path filePath = Paths.get(this.fileName);
            File file = new File(filePath.toString());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                Contact contact = new Contact(parts[0], parts[1]);
                list.add(contact);
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
        
    }


    
}
