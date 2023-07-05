package aula6;
//import aula5.Data;
public class ex1 {
        public static void main(String[] args) {
       Aluno al = new Aluno ("Andreia Melo", 9855678,
        new Data(18, 7, 1990), new Data(1, 9, 2018));
       Bolseiro bls = new Bolseiro ("Igor Santos", 8976543, new Data(11, 5, 1985), 900);
       bls.setBolsa(1050);
       
       System.out.println("Aluno: " + al.getName());
       System.out.println(al);
       
       System.out.println("Bolseiro: " + bls.getName() + ", NMec: "
       + bls.getnmeca() + ", Bolsa: " + bls.getbolsa());
       System.out.println(bls);
    }
    
    
}
