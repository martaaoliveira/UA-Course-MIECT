package aula7;
import java.util.Scanner;
public class ex3 {
    public static final Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Nome da agencia");
        String nome = ler.nextLine();
        System.out.println("endereço da agencia");
        String endereco = ler.nextLine();
        agenciaviagens a = new agenciaviagens(nome, endereco);
        int nr;
        
        do {
            System.out.println("\n\nOperações");
            System.out.println("1 - adicionar alojamento");
            System.out.println("2 - adicionar viatura");
            System.out.println("3 - listar carros");
            System.out.println("4 - check in em alojamento");
            System.out.println("5 - check out em alojamento");
            System.out.println("6 - levantar viatura");
            System.out.println("7 - entregar viatura");
            nr = ler.nextInt();
            switch (nr) {
                case 1:
                    a.addAlojamento(adicionarAlojamento());
                    break;
                case 2:
                    a.addViatura(adicionarViatura());
                    break;
                case 3:
                    for (carro c : a.getViaturas()) {
                        System.out.println(c);
                    }
                    break;
                case 4:
                    checkIn(a);
                    break;
                case 5:
                    checkOut(a);
                    break;
                case 6:
                    levantarViatura(a);
                    break;
                case 7:
                    entregarViatura(a);
                    break;
            }
        } while (nr != 0);
    }

    

    public static alojamento adicionarAlojamento() {
        System.out.print("Codigo:\n ");
        String codigo = ler.next();
        System.out.print("Nome:");
        String nome = ler.next();
        System.out.print("local:");
        String local = ler.next();
        System.out.print("preço por noite:");
        double precoNoite = ler.nextDouble();
        System.out.print("avaliação:");
        double avaliacao = ler.nextDouble();
        alojamento aloj;
        System.out.print("\n");
        System.out.print("Quarto de Hotel ou Apartamento:") ;
        String place = ler.next();

        if (place.toLowerCase().equals("apartamento")) {
            System.out.println("numero de quartos: ");
            int numQuartos = ler.nextInt();
            aloj = new apartamento(codigo, nome, local, precoNoite, avaliacao, numQuartos);
        } 
        if (place.toLowerCase().equals("Quarto de Hotel")) {
            System.out.println("tipo: ");
            String tipo = ler.nextLine();
            aloj = new quartoHotel(codigo, nome, local, precoNoite, avaliacao, tipo);
        }
        else{
            System.out.println("Pedido invalido");
            System.exit(1);
            aloj=null;
        }
        return aloj;
    }

    public static carro adicionarViatura() {
        System.out.println("Classe do carro (A a F):");
        char classe=ler.next().charAt(0);
        System.out.println("Tipo de combustivel ");
        String combustivel = ler.next();
        return new carro(classe, combustivel);
    }

    public static void checkIn(agenciaviagens agencia) {
        System.out.println("Qual e o codigo do alojamento para o qual quer dar checkin");
        String codigo = ler.next();
        for (alojamento a : agencia.getAlojamentos()) {
            if (a.getCodigo().equals(codigo)) {
                if (!a.checkIn()) {
                    System.out.println("Alojamento indisponível!");
                    return;
                } else {
                    return;
                }
            }
        }
        System.out.println("Alojamento inexistente!");
    }

    public static void checkOut(agenciaviagens agencia) {
        System.out.println("Qual e o codigo do alojamento para o qual quer dar checkout");
        String codigo = ler.next();
        for (alojamento a : agencia.getAlojamentos()) {
            if (a.getCodigo().equals(codigo)) {
                if (!a.checkOut()) {
                    System.out.println("Checkout não sucedido!");
                    return;
                } else {
                    return;
                }
            }
        }
        System.out.println("Alojamento inexistente!");
    }

    public static void levantarViatura(agenciaviagens agencia) {
        System.out.println("Qual nr de carro que quer levantar?:");
        int nr = ler.nextInt();
        if (nr > agencia.getViaturas().size() || nr <= 0) {
            System.out.println("Posição inválida!");
            return;
        }
        if (!agencia.getViaturas().get(nr-1).levantar()) {
            System.out.println("Viatura indisponível!");
        } else {
            return;
        }
    }

    public static void entregarViatura(agenciaviagens agencia) {
        System.out.println("Qual nr de carro que quer levantar?:");
        int nr = ler.nextInt();
        if (nr > agencia.getViaturas().size() || nr <= 0) {
            System.out.println("Posição inválida!");
            return;
        }
        if (!agencia.getViaturas().get(nr - 1).entregar()) {
            System.out.println("Entrega não sucedida!");
        } else {
            return;
        }
    }
    
}
