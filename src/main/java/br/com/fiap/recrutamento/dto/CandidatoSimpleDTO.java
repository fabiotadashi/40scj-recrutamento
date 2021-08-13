package br.com.fiap.recrutamento.dto;

import br.com.fiap.recrutamento.entity.Candidato;

public class CandidatoSimpleDTO {

    private Long id;
    private String nome;
    private String cargo;

    public CandidatoSimpleDTO(){}

    public CandidatoSimpleDTO(Candidato candidato) {
        this.id = candidato.getId();
        this.nome = candidato.getNome();
        this.cargo = candidato.getCargo();
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
