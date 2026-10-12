package barbearia.com.br.service;

public class Barbeiro {

    private Long idbarbeiro;
    private String nomebarbeiro;
    private String especialidade;


    public Barbeiro() {
    }

    public Barbeiro(Long idbarbeiro, String nomebarbeiro, String especialidade) {
        this.idbarbeiro = idbarbeiro;
        this.nomebarbeiro = nomebarbeiro;
        this.especialidade = especialidade;

    }
    public Long getId() {
        return idbarbeiro;
    }
    public void setId(Long idbarbeiro) {
        this.idbarbeiro = idbarbeiro;
    }

    public String getNomebarbeiro() {
        return nomebarbeiro;
    }

    public void setNomebarbeiro(String nomebarbeiro){
        this.nomebarbeiro =  nomebarbeiro;
    }

    public String getEspecialidade(){
        return especialidade;
    }

    public void setEspecialidade(String especialidade){

        this.especialidade = especialidade;
    }
}

