package barbearia.com.br.config;

import barbearia.com.br.model.Cliente;
import barbearia.com.br.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInicial implements CommandLineRunner {

    private static final String ADMIN_EMAIL = "ronnie@barbearia.com";
    private static final String ADMIN_SENHA = "123456";

    private final ClienteRepository repo;
    private final PasswordEncoder encoder;

    public AdminInicial(ClienteRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (!repo.existsByEmail(ADMIN_EMAIL)) {
            Cliente admin = new Cliente();
            admin.setNome("Administrador");
            admin.setEmail(ADMIN_EMAIL);
            admin.setSenha(encoder.encode(ADMIN_SENHA));
            admin.setRole("ADMIN");
            repo.save(admin);
        }
    }
}