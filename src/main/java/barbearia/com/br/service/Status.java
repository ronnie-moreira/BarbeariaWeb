package barbearia.com.br.service;

public enum Status {
    AGENDADO ("Agendado"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado");

    private final String descricao;
    Status(String descricao){
        this.descricao = descricao;
        }
    public String getDescricao(){
        return descricao;
    }
}
