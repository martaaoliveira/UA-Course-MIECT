package lab06.ex2;
import java.util.*;
import java.io.*;
public class main {

    public static void main(String[]args){
        // create file contacts.txt and make contacts in it 
        try {
            File contactsFile = new File ("lab06/ex2/contacts.txt");
            contactsFile.createNewFile();
            FileWriter fileWriter = new FileWriter(contactsFile.toString());
            fileWriter.write("Josefina\t999999999\n");
            fileWriter.write("Francisco\t922222222\n");
            fileWriter.write("Felisberto\t911111111\n");
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("IOException");
        }

        //Saving contacts from an existing txt
        System.out.println("Saving contacts from an existing txt");
        // saving these contacts using class ContactsTxt
        ContactsTxt contactsTxt = new ContactsTxt("lab06/ex2/contacts.txt");
        ContactsImplementation contactsImp = new ContactsImplementation();
        contactsImp.openAndLoad(contactsTxt); // load contacts from contacts.txt
        contactsImp.add(new Contact("Mariana", "123456789")); // add Bernardo to the list
        contactsImp.saveAndClose(); // save

        System.out.println("\nDoes Francisco exists on your list?");
        boolean resp1 = contactsImp.exist(contactsImp.getByName("Francisco"));
        if (resp1 == true) {
            System.out.println("Yes");
            contactsImp.remove(contactsImp.getByName("Francisco"));
            contactsImp.saveAndClose(); // salvar lista atualizada no arquivo
        }
        else System.out.println("No");
        
        System.out.println("\n");
        //contacts after removing Francisco
        System.out.println("contacts after removing Francisco");
        contactsImp.openAndLoad(contactsTxt);
        
        System.out.println("\n");
        

        System.out.println("Creating new file txt to put there contacts");
        try {
            File newFile = new File ("lab06/ex2/newContacts.txt");
            newFile.createNewFile();
        } catch (IOException e) {
            System.err.println("IOException");
        }

        ContactsTxt contactsTxt2 = new ContactsTxt("lab06/ex2/newContacts.txt");
        contactsImp.saveAndClose(contactsTxt2);
        ContactsImplementation contactsImp2 = new ContactsImplementation();
        contactsImp2.openAndLoad(contactsTxt2);

    }
}
    

