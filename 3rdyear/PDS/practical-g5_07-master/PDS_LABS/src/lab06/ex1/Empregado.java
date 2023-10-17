package lab06.ex1;

public class Empregado 
{
    private String nome;
    private String apelido;
    private int codigo;
    private double salario;

    public Empregado (String nome, String apelido, int codigo, double salario)
    {   
        this.nome = nome;
        this.apelido = apelido;
        this.codigo = codigo;
        this.salario = salario;
    }

    public String nome()
    {
        return nome;
    }

    public String apelido()
    {
        return apelido;
    }

    public int codigo()
    {
        return codigo;
    }

    public double salario()
    {
        return salario;
    }

    
}
