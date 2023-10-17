package lab06.ex2;


public class Contact {
    //implement the functions on interface contactsinterface
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return phone;
    }

    @Override
    public String toString(){
        return "Contact:" + name + " " + phone +"\t";
    }
}
