package barbearia.com.br.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicos")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idservico;

    private String tipo;
    private double preco;
    private float duracao;

    public Service() {
    }

    public Service(Long idservico, String tipo, double preco, float duracao) {
        this.idservico = idservico;
        this.tipo = tipo;
        this.preco = preco;
        this.duracao = duracao;
    }

    public Long getIdservico() {
        return idservico;
    }

    public void setIdservico(Long idservico) {
        this.idservico = idservico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }
}