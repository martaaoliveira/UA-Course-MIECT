
//Marta Oliveira nº 97613
//Leonardo Fiúza nº	97772


package util;

import static java.lang.System.*;
import java.util.*;
import java.io.*;


public class Input {
  private static Scanner input = new Scanner(System.in);

 
  public static double getDouble(String prompt) {
    double r = 0;
    boolean ok=false;
    while (!ok)
	{
		try
		{
		err.print(prompt);
		String line = input.nextLine();
		r = Double.parseDouble(line.trim());
		ok=true;
		
		}
		catch (NumberFormatException e)
		{
			err.println("Invalido input format");
		}
	}
   return r;
  }


  public static double getDouble(String prompt, double min, double max) {
    assert max>=min :"O minimo não pode ser maior que o maximo";
    double r = 0;
    boolean ok=false;
    while (!ok) {
		try
		{
		err.print(prompt);
		String line = input.nextLine();
		r = Double.parseDouble(line.trim());
		if (min <= r && r <= max) break;
		err.printf("Value must be in range [%f, %f]\n", min, max);
		ok= true;
		}
		catch (NumberFormatException e)
		{
			err.println("Invalido input format");
		}
     
    }
    assert min <= r && r <= max : "Postcondition";
    return r;
  }

}
