package barbearia.com.br.model;

import barbearia.com.br.service.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    // Relacionamento com Cliente (Muitos agendamentos para um cliente)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relacionamento com Barbeiro (Muitos agendamentos para um barbeiro)
    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    // Relacionamento com Serviço (Muitos agendamentos para um serviço)
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Service servico;

    @Enumerated(EnumType.STRING)
    private Status status;            // Ex: "AGENDADO", "CANCELADO", "CONCLUIDO"

    // Construtor vazio (obrigatório para o JPA)
    public Agendamento() {
    }

    // Construtor completo
    public Agendamento(Long id, LocalDateTime dataHora, Cliente cliente, Barbeiro barbeiro, Service servico, Status status) {
        this.id = id;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.status = status;
    }

    // Getters e Setters
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}