package barbearia.com.br.controller;

import barbearia.com.br.model.Barbeiro;
import barbearia.com.br.repository.BarbeiroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controller diz ao Spring: "esta classe recebe requisições do navegador
// e devolve páginas HTML (templates do Thymeleaf)".
@Controller
public class BarbeiroController {

    // O controller precisa do repository para falar com o banco de dados.
    // "final" significa que ele é definido uma vez só, no construtor.
    private final BarbeiroRepository repo;

    // INJEÇÃO POR CONSTRUTOR: o Spring cria o BarbeiroRepository sozinho
    // e entrega aqui quando cria o controller. Por isso não precisa de
    // "new" nem de @Autowired.
    public BarbeiroController(BarbeiroRepository repo) {
        this.repo = repo;
    }

    // @GetMapping: quando o navegador abrir o endereço /barbeiros
    // (digitando na barra ou clicando num link), este método roda.
    @GetMapping("/barbeiros")
    public String listar(Model model) {
        // "Model" é a caixa que leva dados do Java para o HTML.
        // repo.findAll() faz um SELECT * FROM barbeiros.
        // O nome "barbeiros" é o que você usa no HTML: th:each="b : ${barbeiros}"
        model.addAttribute("barbeiros", repo.findAll());

        // O texto devolvido é o NOME do template.
        // "barbeiros" -> o Spring abre templates/barbeiros.html
        return "barbeiros";
    }

    // @PostMapping: roda quando um <form method="post"> é enviado
    // para /barbeiros/salvar. POST é usado para ações que MUDAM dados.
    @PostMapping("/barbeiros/salvar")
    public String salvar(
            // @RequestParam pega o valor do campo do formulário.
            // O nome precisa ser igual ao atributo name="" do <input>:
            // <input name="nome"> -> String nome
            @RequestParam String nome,

            // required = false: o campo é opcional. Se vier vazio, não dá erro.
            @RequestParam(required = false) String especialidade) {

        // Monta um objeto Barbeiro com os dados recebidos do formulário.
        Barbeiro b = new Barbeiro();
        b.setNome(nome);
        b.setEspecialidade(especialidade);

        // save() faz o INSERT no banco. Como o id está vazio, o JPA
        // entende que é um barbeiro novo e gera o id automaticamente.
        repo.save(b);

        // "redirect:" manda o navegador abrir /barbeiros de novo.
        // Sem isso, se você atualizasse a página (F5), o formulário seria
        // reenviado e criaria o barbeiro duplicado.
        return "redirect:/barbeiros";
    }

    // {id} na URL é uma variável: /barbeiros/deletar/5 -> id = 5
    @PostMapping("/barbeiros/deletar/{id}")
    public String deletar(
            // @PathVariable pega o valor que veio dentro da URL ({id}).
            @PathVariable Long id) {

        // deleteById() faz o DELETE FROM barbeiros WHERE id = ?
        repo.deleteById(id);
        return "redirect:/barbeiros";
    }
}