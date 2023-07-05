package aula9;
import java.util.*;

public class ALDemo {
public static void main(String[] args) {
ArrayList<Integer> c1 = new ArrayList<>();
Set<Pessoa>c3= new HashSet<>();
Set<Data> c4 = new TreeSet<>();

for (int i = 10; i <= 100; i+=10)
c1.add(i);
System.out.println("Size: " + c1.size());
for (int i = 0; i < c1.size(); i++)
System.out.println("Elemento: " + c1.get(i));
ArrayList<String> c2 = new ArrayList<>();
c2.add("Vento");
c2.add("Calor");
c2.add("Frio");
c2.add("Chuva");
System.out.println(c2);
Collections.sort(c2);
System.out.println(c2);
c2.remove("Frio");
c2.remove(0);
System.out.println(c2);

c3.add(new Pessoa("Mariana", 52525252, new Data(12, 03, 2001)));
c3.add(new Pessoa("Eduardo", 25658552, new Data(26, 04, 2001)));
c3.add(new Pessoa("Joao", 85858585, new Data(7, 5, 1999)));
c3.add(new Pessoa("Rafael", 9696965, new Data(25, 4, 2001)));
c3.add(new Pessoa("Tiago", 74752625, new Data(7, 5, 20001)));
c3.add(new Pessoa("Tiago", 74752625, new Data(7, 5, 20001)));
for(Pessoa p:c3){
    System.out.println(p);
}

c4.add(new Data(2, 11, 2002));
c4.add(new Data(2, 11, 2002));
c4.add(new Data(7, 5, 2002));
c4.add(new Data(30, 11, 2000));
c4.add(new Data(21, 7, 2001));
c4.add(new Data(3, 1, 1994));
for(Data p : c4) {
    System.out.println(p);
}
Data c4D = new Data(3, 11, 1994);
System.out.println("CompareTo: 3/1/1994 com 3/1/1994  "+c4D.compareTo(new Data(3,1,1994)));
//System.out.println("Equals: 3/1/1994 com 3/1/1994  "+c4D.equals(new Data(3,1,1994)));
}
}