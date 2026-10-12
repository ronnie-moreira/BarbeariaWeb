package barbearia.com.br.service;

import java.time.LocalDateTime;

public class Agendamento {

    private Long id;                  // Long é o padrão recomendado para IDs de banco de dados
    private LocalDateTime dataHora;   // Tipo próprio do Java para data e hora
    private Cliente cliente;          // Objeto Cliente em vez de apenas String
    private Barbeiro barbeiro;        // Objeto Barbeiro
    private Service servico;          // Objeto Servico
    private String status;            // Ex: "AGENDADO", "CANCELADO", "CONCLUIDO"

    // Construtor vazio (obrigatório para frameworks como o Spring/JPA)
    public Agendamento() {
    }

    // Construtor completo
    public Agendamento(Long id, LocalDateTime dataHora, Cliente cliente, Barbeiro barbeiro, Service servico, String status) {
        this.id = id;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.status = status;
    }

    // Getters e Setters (necessários para acessar e alterar os dados)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Service getServico() {
        return servico;
    }

    public void setServico(Service servico) {
        this.servico = servico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
