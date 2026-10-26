package barbearia.com.br.service;

import barbearia.com.br.model.Cliente;
import barbearia.com.br.repository.ClienteRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public UsuarioDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente c = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User.withUsername(c.getEmail())
                .password(c.getSenha())
                .roles(c.getRole())   // vira ROLE_ADMIN ou ROLE_CLIENTE
                .build();
    }
}