import java.util.Scanner;
import java.io.*;
public class ex5_4 {2
	
	public static void main (String[] args)throws IOException {
		String dir=null;
		File file;
		try
		{
			dir=args[0];
			file=new File(dir);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			dir=".\\";
			file = new File(dir);
		}
		finally	{
			System.out.println("o diretorio onde se encontra é " + dir);
		}
		
		
		
		File dfile[]=file.listFiles();
		int i=0;
		for (int j = dfile.length; i <j; i++)
		{
			File arquivos= dfile[i];
			System.out.println(arquivos.getName()+ "\t");
			if(!arquivos.isFile())System.out.println("Dir");
			if(arquivos.canWrite())System.out.print("W");
			if(arquivos.canRead())System.out.println("R");
		}
		
		
	}
}

