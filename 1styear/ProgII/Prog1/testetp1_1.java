
import java.util.Scanner;
public class testetp1_1{
	static Scanner ler=new Scanner(System.in);
	
	public static void main (String[] args) {
		
int[]grades;
int[]histogram;
double frequency_average=0, total_average = 0;
System.out.printf("Introduza nota dos alunos(nota0-10;11-faltou: %n");
grades = getGrades(10);

//lista notas
list(grades);

//calcula histograma
histogram=makeHistogram(grades);
//imprime histograma
printHistogram(histogram);	

System.out.printf("Media total %2.1f, Media alunos freq. =%2.1f",total_average, frequency_average);

											}
	
public static int[] getGrades(int number_of_grades) 
{
	int[] grades = new int[number_of_grades];	//??

	for (int i = 0; i < grades.length; i++) //number_of_grades
	{	
		System.out.printf("Nota aluno %3d: %d\n",i+1,grades[i]);
		int grade=ler.nextInt();	
		grades[i]=grade;
		if (grade<0 || grade>11)
		{
			System.out.println("Nota inválida");
		}
	
	}
		
	System.out.println();
		
	return grades;	
}
	
	
public static void list(int[]grades){

	System.out.printf("Lista Notas: ");
		for (int i = 0; i < grades.length; i++)
	{
		System.out.printf("%d,",grades[i]);
	}
	System.out.println();
	
								    }
public static int[] makeHistogram(int[] grades){
	int[]histogram=new int[12];
	for (int i = 0; i < grades.length; i++)
	{
		histogram[grades[i]]++;
	}
	
	
	
return histogram;
											}
											
public static void printHistogram(int[]histogram){
	for (int i = 0; i <histogram.length; i++)
	{
		System.out.printf(" Nota %2d:",i);
		for (int j = 0; j <histogram[i]; j++)
		{
			System.out.print("*");
		}
		System.out.println();
	}
	
												}
public static double average(int[] grades,char type) 
{
	double sum=0;
	int count=0;
	switch(type) {
		case 'f':
		
		 for (int i=0; i<grades.length; i++)
		 {
			 if (grades[i] == 11)
			 {
				 continue;
			 }
			sum += grades[i]; 
			count++;
		 }
		 
		break;
		
		case 't':
		
		for (int i=0; i<grades.length;i++)
		 {
			 if(grades[i] == 11){
				 count++;
				 continue;
				 //a soma é 0;
			 }
			sum += grades[i]; 
			count++;
		 }
		
		
		break;
		
		default:
		System.out.println("caso inexistente");
		System.exit(1);
		break;
				}
			return sum/count;
}																	    
}

