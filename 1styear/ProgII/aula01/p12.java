import static java.lang.System.*;
import java.util.Scanner;

// Complete o programa para calcular a nota final NF de
// um aluno de Programação 2 (2019-2020) na época normal.
public class p12
{
  public static final Scanner in = new Scanner(System.in);

  // Nota mínima em cada componente:
  public static final double NOTA_MINIMA = 6.5;

  public static void main(String[] args)
  {
    double atp1 = readInRange("ATP1? ", 0.0, 20.0);
    double ap1 = readInRange("AP1? ", 0.0, 20.0);
    double atp2 = readInRange("ATP2? ", 0.0, 20.0);
    double ap2 = readInRange("AP2? ", 0.0, 20.0);

    // Use estas variáveis para calcular o resultado:
    double ctp; // componente TP
    double cp;  // componente P
    int nf;     // nota final (inteiro positivo ou -66 se RNM)
                // Use Math.round para arredondar (procure a documentação)
    boolean aprovado;   // indica se passou ou não

    // Complete com os cálculos e variáveis auxiliares:
    //...

    out.printf(" %7s %7s %7s %7s\n", "CTP", "CP", "NF", "APROV?");
    out.printf(" %7.1f %7.1f %7d %7s\n", ctp, cp, nf, aprovado);
  }

  // Reads a double that must be in the range [min, max].
  // Shows the prompt, reads a double value.
  // If the value is not in the range, prints a warning and repeats.
  public static double readInRange(String prompt, double min, double max)
  {
    assert min <= max : "max must be at least as large as min!";
    double value;
    //...
    return value;
  }
}
