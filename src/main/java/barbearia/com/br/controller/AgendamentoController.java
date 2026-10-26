package barbearia.com.br.controller;

import barbearia.com.br.model.Agendamento;
import barbearia.com.br.model.Cliente;
import barbearia.com.br.repository.BarbeiroRepository; // <--- 1. Importe o repository do barbeiro
import barbearia.com.br.repository.ClienteRepository;
import barbearia.com.br.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository; // <--- 2. Injete o repositório aqui

    @GetMapping("/agendamentos")
    public String listarAgendamentos(Model model) {
        model.addAttribute("agendamentos", agendamentoService.listarTodos());
        return "agendamentos";
    }

    @GetMapping("/agendamentos/novo")
    public String abrirFormulario(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        // Envia a grade de horários (09:00 até 21:00, a cada 30 min) para o <select> do HTML
        model.addAttribute("horariosPermitidos", agendamentoService.obterHorariosPermitidos());

        // <--- 3. ADICIONADO: Envia a lista de barbeiros para preencher o <select> na tela
        model.addAttribute("barbeiros", barbeiroRepository.findAll());

        return "formulario";
    }

    @PostMapping("/agendamentos/salvar")
    public String salvarAgendamento(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                    @RequestParam("hora") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hora,
                                    Agendamento agendamento,
                                    Model model,
                                    Authentication authentication) {
        try {
            // Junta a data e o horário selecionados no formulário
            agendamento.setDataHora(LocalDateTime.of(data, hora));

            boolean admin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            // Cliente comum: o sistema define o cliente logado, ignorando o que vier do formulário
            if (!admin) {
                Cliente logado = clienteRepository
                        .findByEmail(authentication.getName())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
                agendamento.setCliente(logado);
            }

            agendamentoService.salvar(agendamento);
            return "redirect:/agendamentos?agendado";

        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("agendamento", agendamento);
            // Recarrega os horários permitidos caso dê erro na tentativa de salvar
            model.addAttribute("horariosPermitidos", agendamentoService.obterHorariosPermitidos());

            // <--- 4. ADICIONADO: Recarrega os barbeiros também caso caia no erro
            model.addAttribute("barbeiros", barbeiroRepository.findAll());

            return "formulario";
        }
    }

    @PostMapping("/agendamentos/cancelar/{id}")
    public String cancelar(@PathVariable Long id) {
        agendamentoService.cancelar(id);
        return "redirect:/agendamentos?cancelado";
    }

    @PostMapping("/admin/agendamentos/concluir/{id}")
    public String concluir(@PathVariable Long id) {
        agendamentoService.concluir(id);
        return "redirect:/agendamentos";
    }

    @GetMapping("/agendamentos/deletar/{id}")
    public String deletarAgendamento(@PathVariable Long id) {
        agendamentoService.deletar(id);
        return "redirect:/agendamentos";
    }
}