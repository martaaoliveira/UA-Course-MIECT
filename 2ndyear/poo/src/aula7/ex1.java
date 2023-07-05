package aula7;
import java.util.Scanner;
public class ex1 {
    public static final Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {
       
        //Triangle triangulo[] = new Triangle[10];
        //Rectangle retangulo[] = new Rectangle[10];
        int equals=0;
        while(true) {
            System.out.println("");
			System.out.println("\nLets create a figure:");
			System.out.println("1 - create new circle");
			System.out.println("2 - create new triangle");
			System.out.println("3 - create new rectangle");
			System.out.println("0 - exit");
			System.out.print("\nOption: ");
            int k;
            int i=0;
			int op = ler.nextInt();
            switch(op){
        
            case 1:
            equals=0;
            do{
            System.out.println("Quantos cirulos quer criar");
            k=ler.nextInt();
            if(k<0){
                System.out.println("Numero invalido");
                System.exit(1);
            }
            }while(k<0);
            double radius[]=new double [k];
            circulo circle[] = new circulo[k];
            String cor []=new String[k];;
            do{
            for(int r=0;r<k;r++){
            System.out.println("Insira um valor do raio");
            radius[r]= ler.nextDouble();
            System.out.println("Insira uma cor");
            cor[r]=ler.next();
            }
        
            k--;
            }while(k<0);

            for(i=0; i<circle.length; i++) {
            circle[i] = new circulo(cor[i],radius[i]);
            System.out.printf("Circulo  %s: %s %s P:%.2f A:%.2f\n",i, circle[i],cor[i], circle[i].perimetro(), circle[i].area());
            }
            equals=0;
            for (i = 0; i < k; i++) {
                if(circle[i].equals(circle[i+1])){
                    equals++;
                }
            }
            System.out.println("Existem " + equals + " circulos iguais!");
            
            break;

            case 2:
            do{
                equals=0;
                System.out.println("Quantos triangulos quer criar");
                k=ler.nextInt();
                if(k<0){
                    System.out.println("Numero invalido");
                    System.exit(1);
                }
                }while(k<0);
                double l1[]=new double [k];
                double l2[]=new double [k];
                double l3[]=new double [k];
                triangulo triangle [] = new triangulo[k];
                String cor2[]=new String[k];
                do{
                    for(int r=0;r<k;r++){
                    System.out.println("Insira um valor do lado 1 do triangulo " + r);
                    l1[r]= ler.nextDouble();
                    System.out.println("Insira um valor do lado 2 do triangulo "+r);
                    l2[r]=ler.nextDouble();
                    System.out.println("Insira um valor do lado 3 do triangulo " + r);
                    l3[r]=ler.nextDouble();
                    System.out.println("Insira uma cor");
                    cor2[r]=ler.next();
                    }
                k--;
                }while(k<0);
    
                for(i=0; i<triangle.length; i++) {
                    triangle[i] = new triangulo(cor2[i],l1[i],l2[i],l3[i]);
                System.out.printf("Triagulo %s: %s %s P:%.2f A:%.2f\n",i, triangle[i],cor2[i],triangle[i].perimetro(), triangle[i].area());
                }

                for (i = 0; i < k; i++) {
                    if(triangle[i].equals(triangle[i+1])){
                        equals++;
                    }
                        
                }
                System.out.println("Existem " + equals + " triangulos iguais!");

            break;


            case 3:
            equals=0;
            do{
                System.out.println("Quantos retangulos quer criar");
                k=ler.nextInt();
                if(k<0){
                    System.out.println("Numero invalido");
                    System.exit(1);
                }
                }while(k<0);

                double comprimento[]=new double [k];
                double largura[]=new double [k];
                retangulo rectangle [] = new retangulo[k];
                String cor3 []= new String[k];
                do{
                    for(int r=0;r<k;r++){
                    System.out.println("Insira o valor do comprimento do retangulo "+r);
                    comprimento[r]= ler.nextDouble();
                    System.out.println("Insira um valor da largura do retangulo  "+r);
                    largura[r]=ler.nextDouble();
                    System.out.println("Insira uma cor para o retangulo "+r);
                    cor3[r]=ler.next();
                    }
                k--;
                }while(k<0);
    
                for(i=0; i<rectangle.length; i++) {
                    rectangle[i] = new retangulo(cor3[i],comprimento[i],largura[i]);
                System.out.printf("Retangulo %s: %s %s P:%.2f A:%.2f\n",i, rectangle[i], cor3[i],rectangle[i].perimetro(), rectangle[i].area());
                }

                equals=0;
                for (i = 0; i < k; i++) {
                    if(rectangle[i].equals(rectangle[i+1])){
                        equals++;
                    }
                }
                System.out.println("Existem " + equals + " retangulos iguais!");

            break;

            default:
            System.exit(1);
            break;
            }


    }

}

}
