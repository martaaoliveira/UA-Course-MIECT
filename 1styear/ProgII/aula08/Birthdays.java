// Marta Oliveira nº 97613
//Bruno Silva nº 97931
//Francisco Cardita 97640
//Leonardo Fiuza nº 97772
import static java.lang.System.*;
import p2utils.*;

// Analise e complete este programa.
// Experimente chamar, por exemplo:
//
// java -ea Birthdays 1867-09-07 Curie 1879-03-14 Einstein 1809-02-12 Darwin

public class Birthdays
{
  public static void main(String[] args) {
    if (args.length % 2 != 0) {
      err.println("Erro: número de argumentos inválido");
      err.println("Utilização: java Birthdays data1 nome1 data2 nome2 ...");
      err.println("Cada data no formato YYYY-MM-DD.");
      exit(1);
    }

   SortedList<Pessoa> list = new SortedList<Pessoa>();

  for(int i=0; i<args.length/4; i++) {
      try {
        int d = Integer.parseInt(args[4*i]);
        int m = Integer.parseInt(args[4*i+1]);
        int y = Integer.parseInt(args[4*i+2]);
        String name = args[4*i+3];   
       list.insert( new Pessoa(name, new Data(d, m, y)) );
      }
      catch (NumberFormatException e) {
        out.println("Erro: formato inválido (informação não registada)");
      }
    }

    //list.print(); //testar
    out.println(list);
  }
}



