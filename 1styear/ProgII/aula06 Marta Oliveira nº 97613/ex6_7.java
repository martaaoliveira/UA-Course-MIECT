

import java.io.*;
public class ex6_7 {
	
	public static void main (String[] args) {
		try{
			listDirs(args[0], args[1]);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Argumento inválido");
		}
		
		
	}
	
	
	
	
	public static void listDirs(String args, String text){
		File file = null;
		
		try{
			file = new File(args);
			if (!file.exists()) throw new FileNotFoundException();
		}
		catch(FileNotFoundException e){
			System.out.println("O ficheiro não existe.");
			System.exit(1);
		}
		
		File [] files = file.listFiles();
		
		for(int i=0;i<files.length;i++){
			if(files[i].getName().indexOf(text)!=-1)
				System.out.println(files[i].getPath());
			if(!files[i].isFile())
				listDirs(files[i].getPath(), text);
		}
	}

	

}

