package lab06.ex2;
import java.util.*;
public interface ContactsStorageInterface {
    public boolean saveContacts(List<Contact> list);
    public List<Contact> loadContacts();
}