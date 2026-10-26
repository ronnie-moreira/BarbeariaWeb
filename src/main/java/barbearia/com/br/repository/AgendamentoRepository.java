package barbearia.com.br.repository;

import barbearia.com.br.model.Agendamento;
import barbearia.com.br.service.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByStatus(Status status);

    // Todos (admin), ordenados por data
    List<Agendamento> findAllByOrderByDataHoraAsc();

    List<Agendamento> findByClienteId(Long clienteId);

    // Só os do cliente logado
    List<Agendamento> findByClienteEmailOrderByDataHoraAsc(String email);

    // Conflito: mesmo barbeiro, mesmo horário, ignorando o status informado (CANCELADO)

    boolean existsByBarbeiroIdAndDataHoraAndStatusNot(Long barbeiroId, LocalDateTime dataHora, Status status);

    boolean existsByDataHoraAndStatus(LocalDateTime dataHora, Status status);

}