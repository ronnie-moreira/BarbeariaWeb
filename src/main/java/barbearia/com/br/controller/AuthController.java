package barbearia.com.br.controller;

import barbearia.com.br.model.Cliente;
import barbearia.com.br.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final ClienteRepository repo;
    private final PasswordEncoder encoder;

    public AuthController(ClienteRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String registrar(@RequestParam String nome,
                            @RequestParam String telefone,
                            @RequestParam String email,
                            @RequestParam String senha,
                            Model model) {

        if (repo.existsByEmail(email)) {
            model.addAttribute("erro", "Este e-mail ja esta cadastrado.");
            return "cadastro";
        }
        if (senha.length() < 6) {
            model.addAttribute("erro", "A senha precisa ter pelo menos 6 caracteres.");
            return "cadastro";
        }

        Cliente c = new Cliente();
        c.setNome(nome);
        c.setTelefone(telefone);
        c.setEmail(email);
        c.setSenha(encoder.encode(senha));
        c.setRole("CLIENTE");
        repo.save(c);

        return "redirect:/login?cadastrado";
    }
}