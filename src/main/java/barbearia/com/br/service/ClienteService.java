package barbearia.com.br.service;

import barbearia.com.br.model.Cliente;
import barbearia.com.br.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }
    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }


}
