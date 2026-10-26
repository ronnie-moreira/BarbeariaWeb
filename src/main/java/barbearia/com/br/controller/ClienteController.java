package barbearia.com.br.controller;

import barbearia.com.br.model.Cliente;
import barbearia.com.br.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes"; // Vai procurar clientes.html
    }

    @GetMapping("/clientes/novo")
    public String abrirFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formulario-cliente"; // Vai procurar formulario-cliente.html
    }

    @PostMapping("/clientes/salvar")
    public String salvarCliente(Cliente cliente) {
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteService.deletar(id);
        return "redirect:/clientes";
    }
}