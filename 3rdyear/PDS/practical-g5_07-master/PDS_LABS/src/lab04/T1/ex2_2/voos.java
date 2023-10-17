
package lab04.T1.ex2_2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class voos {
    
    public static void main(String[] args) throws FileNotFoundException {
        List<voo> lvoos = new ArrayList<voo>();
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Escolha uma opçao: (H para ajuda)");
            String[] line = sc.nextLine().split(" ");

            if (line[0].equals("H")){
                System.out.println(" I - filename (ler ficheiro) \n M - flight_code - (mostrar mapa de reservas) \n F - flight_code (code) executive_seats tourist_seats (1x2 format) - (criar novo voo) \n R - flight_code class (T/E) number_seats - (nova reserva)\n C - reservation_code - (Cancelar reserva) \n Q - Sair");
            }

            else if (line[0].equals("I")){
                File f = new File("ex2_2/" + line[1]);
                Scanner sc2 = new Scanner(f);
                Scanner sc3 = new Scanner(f);
                String[] line5 = sc3.nextLine().split(" ");
                String codigo1 = line5[0].substring(1, line5[0].length());
                String executivos1 = "";
                String turisticos1 = "";
                if(line5.length == 2){
                    turisticos1 = line5[1];
                    executivos1 = "0x0";
                }else{
                    executivos1 = line5[1];
                    turisticos1 = line5[2];
                }
                
                aviao a1 = new aviao(executivos1, turisticos1);
                if (a1.getnexecutiva() == 0 && a1.getnturistica() == 0)
                    System.out.println("Código do voo: " + codigo1 + " | Sem lugares disponíveis");
                else if (a1.getnexecutiva() == 0){
                    System.out.println("Código do voo: " + codigo1 + " | Turisticas: " + a1.getnturistica());
                    System.out.println("Classe executiva nao disponivel neste voo");}
                else if (a1.getnturistica() == 0){
                    System.out.println("Código do voo: " + codigo1 + " | Executivas: " + a1.getnexecutiva());
                    System.out.println("Classe turistica nao disponivel neste voo");}
                else
                    System.out.println("Código do voo: " + codigo1 + " | Executivas: " + a1.getnexecutiva() + " | Turísticas: " + a1.getnturistica());
                sc3.close();

                int c = 0;
                String executivos = "";
                String turisticos = "";
                String codigo = "";
                aviao a = null;
                voo v = null;
                while (sc2.hasNextLine()){
                    if (c == 0){
                        String[] line2 = sc2.nextLine().split(" ");
                        codigo = line2[0].substring(1, line2[0].length());
                        if(line2.length == 2){
                            turisticos = line2[1];
                            executivos = "0x0";
                        }else{
                            executivos = line2[1];
                            turisticos = line2[2];
                        }
                        a = new aviao(executivos, turisticos);
                        v = new voo(codigo,a);
                        c++;
                    }
                    else{
                        String[] line2 = sc2.nextLine().split(" ");
                        String tipo = line2[0];
                        int n = Integer.parseInt(line2[1]);
                        v.reserva(tipo, n);
                    }      
                }
                lvoos.add(v);
                sc2.close();
            }
            else if (line[0].equals("M")){
                if (line.length != 2){
                    System.out.println("Invalid command");
                    continue;
                }
                for (voo v : lvoos){
                    if (v.codigo.equals(line[1])){
                        v.Disp();
                    }
                }
            }
            else if (line[0].equals("F")){
                if (line.length != 4){
                    System.out.println("Invalid command");
                    continue;
                }
                String codigo = line[1];
                String executivos = line[2];
                String turisticos = line[3];
                aviao a = new aviao(executivos, turisticos);
                voo v = new voo(codigo,a);
                lvoos.add(v);

            }
            else if (line[0].equals("R")){
                if (line.length != 4){
                    System.out.println("Invalid command");
                    continue;
                }
                for (voo v : lvoos){
                    if (v.codigo.equals(line[1])){
                        v.setNreserva("");
                        v.reserva(line[2], Integer.parseInt(line[3]));
                        if (!v.getNreserva().equals(""))
                            System.out.printf("%7s = %-40s\n",v.getCodigo(),v.getNreserva().substring(0, v.getNreserva().length()-1));
                    }
                }
                
                
            }
            else if (line[0].equals("C")){
                if (line.length != 2){
                    System.out.println("Invalid command");
                    continue;
                }
                for (voo v : lvoos){
                    if (v.codigo.equals(line[1])){
                        v.cancelar(Integer.parseInt(line[2]));
                    }
                }
            }
            else if (line[0].equals("Q")){
                
                //exit program
                break;
            }
            else{
                System.out.println("Invalid command");

            }
            
        }
        sc.close();
        
        
      
    }
}
