package aula8;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ex1_b {
    public static void main(String[] args) {
        List<viatura> viaturas = new ArrayList<>();
        viaturas.add(new motociclo("zzz", "yamah", "yzf", 900, "desportivo"));
        viaturas.add(new ligeiro("20-AC-70", "renault", "megane", 90, 4, 230));
        viaturas.add(new taxi("zzz", "renault", "clio", 100, 5, 120, 557));
        viaturas.add(new PesadoEletrico("40-DE-60", "mercedes", "3a", 1000, 3, 10000, 70000,2000));
        
    
        int maxKm=0;
        List<Integer> random_trajeto = new ArrayList<>();
        
        int minVal=5;
        int maxVal=200;

        for(int i=0;i<6;i++){
            int random=ThreadLocalRandom.current().nextInt(minVal,maxVal);
            random_trajeto.add(random);
        }
       
        //viatura viatura_maiorDistancia=viaturas.get(0);

        int i=0;
        for (viatura viatura : viaturas) {
            
            viatura.trajeto(random_trajeto.get(i));
            i++;
            //viatura.trajeto(random_trajeto.get(viaturas.indexOf(viatura))+1);
            
            if (viatura instanceof VeiculoEletrico) {
                System.out.printf(" %s com autonomia: %d\n", viatura, ((VeiculoEletrico) viatura).autonomia());
            }
        }


    }


    }

