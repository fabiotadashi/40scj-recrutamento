package br.com.fiap.recrutamento.dto;

import br.com.fiap.recrutamento.entity.Candidato;

import java.time.LocalDate;

public class CandidatoDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String curriculo;
    private String email;
    private String telefone;
    private String cargo;

    public CandidatoDTO(){}

    public CandidatoDTO(Candidato candidato) {
        this.id = candidato.getId();
        this.nome = candidato.getNome();
        this.dataNascimento = candidato.getDataNascimento();
        this.curriculo = candidato.getCurriculo();
        this.email = candidato.getEmail();
        this.telefone = candidato.getTelefone();
        this.cargo = candidato.getCargo();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
