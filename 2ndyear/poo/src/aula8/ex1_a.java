package aula8;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ex1_a {
    public static void main(String[] args) {
        List<viatura> viaturas = new ArrayList<>();
        viaturas.add(new motociclo("zzz", "yamah", "yzf", 900, "desportivo"));
        viaturas.add(new ligeiro("20-AC-70", "renault", "megane", 90, 4, 230));
        viaturas.add(new taxi("zzz", "renault", "clio", 100, 5, 120, 557));
        viaturas.add(new pesadoMercadorias("40-DE-60", "mercedes", "3a", 1000, 3, 10000, 70000));
        viaturas.add(new pesadoPassageiros("30-BD-70", "toyota", "5c", 3000, 3, 5000, 27));
    
        int maxKm=0;
        List<Integer> random_trajeto = new ArrayList<>();
        
        int minVal=5;
        int maxVal=200;

        for(int i=0;i<6;i++){
            int random=ThreadLocalRandom.current().nextInt(minVal,maxVal);
            random_trajeto.add(random);
        }
       
        viatura viatura_maiorDistancia=viaturas.get(0);

        int i=0;
        
        for (viatura viatura : viaturas) {
            
            viatura.trajeto(random_trajeto.get(i));
           
            //viatura.trajeto(random_trajeto.get(viaturas.indexOf(viatura))+1);
            
            
         System.out.println(viaturas.get(i));
         System.out.println("\n");
         

            if (viaturas.get(i)!=viaturas.get(i+1)){
                System.out.println("As 2 viaturas sao diferentes");

            }

          

            if(viatura.distanciaTotal()>maxKm){
                maxKm=viatura.distanciaTotal();
                viatura_maiorDistancia=viatura;
            }

            i++;

            if(i==viaturas.size()-1){
                break;
            }
            

        }
    



        Empresa_aluguer empresa = new Empresa_aluguer("Empresa ", "xxxx-xxx ", "123@123.pt", viaturas);
        System.out.println("\n\n" + empresa);
        System.out.printf("\n\nViatura com mais distância percorrida: %s", viatura_maiorDistancia);        


    }


    }

