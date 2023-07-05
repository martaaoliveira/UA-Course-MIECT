package aula2;
public class Ex3 {
    public static void main(String[] args){
        
    Double t_i=0.0;
    Double t_f=0.0;
    Double M=0.0;
    Double Q=0.0;
    assert(args.length==3):"Insira 3 argumentos,temperatura inicial, final e a quantidade de agua ";
    try{
        t_i=Double.parseDouble(args[0]);
        t_f=Double.parseDouble(args[1]);
        M=Double.parseDouble(args[2]);

    }catch (NumberFormatException e) {
        System.err.println("Argumentos devem ser numeros.");
        System.exit(1);
    }
    
    if(t_i>t_f){
        System.out.print("Temperatura inicial nao deve ser maior que final!");
        System.exit(1);
    }

    Q = M*(t_f-t_i)*4184;
    System.out.printf("A energia necessaria para aumentar %.2f graus sera: %.2f J \n",t_f,Q);
    }
}
