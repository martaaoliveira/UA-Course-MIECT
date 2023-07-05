package aula7;

import java.util.ArrayList;
import java.util.List;

public class agenciaviagens {
    private String nome;
    private String endereco;
    private List<alojamento> alojamentos;
    private List<carro> viaturas;

    public agenciaviagens(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.alojamentos = new ArrayList<alojamento>();
        this.viaturas = new ArrayList<carro>();
    }

    public void addAlojamento(alojamento other) {
        alojamentos.add(other);
    }

    public void addViatura(carro cars) {
        viaturas.add(cars);
    }

    @Override
    public String toString() {
        return "Agencia com alojamentos=" + alojamentos + ", endereco=" + endereco + ", nome=" + nome + ", viaturas=" + viaturas + "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }



    public void setAlojamentos(List<alojamento> alojamentos) {
        this.alojamentos = alojamentos;
    }

    public void setViaturas(List<carro> viaturas) {
        this.viaturas = viaturas;
    }

    public List<alojamento> getAlojamentos() {
        return alojamentos;
    }

    public List<carro> getViaturas() {
        return viaturas;
    }

 

}
