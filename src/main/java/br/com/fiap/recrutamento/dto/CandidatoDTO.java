package br.com.fiap.recrutamento.dto;

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

    public CandidatoDTO(CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO) {
        this.nome = candidatoCreateOrUpdateDTO.getNome();
        this.dataNascimento = candidatoCreateOrUpdateDTO.getDataNascimento();
        this.curriculo = candidatoCreateOrUpdateDTO.getCurriculo();
        this.email = candidatoCreateOrUpdateDTO.getEmail();
        this.telefone = candidatoCreateOrUpdateDTO.getTelefone();
        this.cargo = candidatoCreateOrUpdateDTO.getCargo();
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
