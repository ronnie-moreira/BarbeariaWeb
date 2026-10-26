package barbearia.com.br.service;

import barbearia.com.br.model.Agendamento;
import barbearia.com.br.service.Status;
import barbearia.com.br.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // 1. Gera a grade fixa de horários (de 30 em 30 minutos, das 09:00 às 21:00)
    public List<LocalTime> obterHorariosPermitidos() {
        List<LocalTime> horarios = new ArrayList<>();
        LocalTime hora = LocalTime.of(9, 0);   // Início às 09:00
        LocalTime fim = LocalTime.of(21, 0);     // Fim às 21:00

        while (!hora.isAfter(fim)) {
            horarios.add(hora);
            hora = hora.plusMinutes(30); // Adiciona 30 minutos a cada ciclo
        }
        return horarios;
    }

    public Agendamento salvar(Agendamento agendamento) {
        if (agendamento.getDataHora() == null) {
            throw new RuntimeException("Informe a data e hora do atendimento.");
        }

        if (agendamento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível agendar para uma data que já passou.");
        }

        // 2. Valida se o horário escolhido pertence obrigatoriamente à grade de horários permitidos
        LocalTime horaAgendada = agendamento.getDataHora().toLocalTime();
        if (!obterHorariosPermitidos().contains(horaAgendada)) {
            throw new RuntimeException("Horário inválido. O agendamento deve ser feito entre 09:00 e 21:00, em intervalos de 30 minutos.");
        }

        // 3. Verifica se o horário já está ocupado no banco de dados
        if (agendamentoRepository.existsByDataHoraAndStatus(
                agendamento.getDataHora(), Status.AGENDADO)) {
            throw new RuntimeException("Este horário já está ocupado. Escolha outro.");
        }

        agendamento.setStatus(Status.AGENDADO);
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> listarPorCliente(Long clienteId) {
        return agendamentoRepository.findByClienteId(clienteId);
    }

    public void cancelar(Long id) {
        Agendamento a = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));
        a.setStatus(Status.CANCELADO);
        agendamentoRepository.save(a);
    }

    public void concluir(Long id) {
        Agendamento a = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));
        a.setStatus(Status.CONCLUIDO);
        agendamentoRepository.save(a);
    }

    public void deletar(Long id) {
        agendamentoRepository.deleteById(id);
    }
}