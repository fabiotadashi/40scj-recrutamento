package br.com.fiap.recrutamento.dto;

public class CandidatoSimpleDTO {

    private Long id;
    private String nome;
    private String cargo;

    public CandidatoSimpleDTO(){}

    public CandidatoSimpleDTO(CandidatoDTO candidatoDTO) {
        this.id = candidatoDTO.getId();
        this.nome = candidatoDTO.getNome();
        this.cargo = candidatoDTO.getCargo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
