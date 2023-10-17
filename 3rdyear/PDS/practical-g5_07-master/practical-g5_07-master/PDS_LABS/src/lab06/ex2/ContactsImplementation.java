package lab06.ex2;
import java.util.*;

public class ContactsImplementation implements ContactsInterface{
    
    protected List<Contact> list;
    protected ContactsStorageInterface store;

    public ContactsImplementation() {
        this.list = new ArrayList<>();
    }
    
    public void openAndLoad(ContactsStorageInterface store) {
        this.store = store;
        list = store.loadContacts();
        System.out.println("Loading contacts, please Wait");
        for (Contact contact : list) {
            System.out.println(contact);
        } 
    }

    public void saveAndClose() {
        boolean store= this.store.saveContacts(this.list);
        System.out.println("Saving contacts, please Wait");
        if(store==true){
            System.out.println("Contacts saved");
        }
        else{
            System.out.println("Contacts not saved");
        }
    }

    
    public void saveAndClose(ContactsStorageInterface store) {
        
        this.store = store;
        boolean store1= store.saveContacts(list);
        System.out.println("Saving contacts, please Wait");
        if(store1==true){
            System.out.println("Contacts saved");
        }
        else{
            System.out.println("Contacts not saved");
        }
    }

    
    public boolean exist(Contact contact) {
        
        boolean exist= false;
        for(Contact c: list){
            
            if(contact.getName().equals(c.getName()) && contact.getPhone().equals(c.getPhone())){
                exist=true;
                break;
            }
            else {exist=false; continue;}
            // if(c.toString().equals(list.toString())){
            //     return true;
            // }

        }
        return exist;

        
    }

   
    public Contact getByName(String name) {
        for(Contact c: list){
            if(c.getName().contains(name)){
                return c;
            }
        }
        System.out.println("Contact Not Found!");
        return null;
    }

    
    public boolean add(Contact contact) {
        return list.add(contact);
    }

    
    public boolean remove(Contact contact) {
        System.out.println("Removing contact "+ contact.getName());
        return list.remove(contact);
    }
}
