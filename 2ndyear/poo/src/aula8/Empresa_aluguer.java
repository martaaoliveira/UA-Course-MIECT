package aula8;
import java.util.ArrayList;
import java.util.List;

public class Empresa_aluguer {

    private String nome;
    private String codigoPostal;
    private String email;
    //private List<viatura> viaturas;
    private List<viatura> viaturas = new ArrayList<viatura>();
        
    public Empresa_aluguer(String nome, String codigoPostal, String email,List<viatura> viaturas) {
        this.nome = nome;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.viaturas = viaturas;
    
    }


    @Override
    public String toString() {
        return "Empresa com codigoPostal=" + codigoPostal + ", email=" + email + ", nome=" + nome + ",com viaturas=" + viaturas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<viatura> getViaturas() {
        return viaturas;
    }

    public void setViaturas(List<viatura> viaturas) {
        this.viaturas = viaturas;
    }

}

