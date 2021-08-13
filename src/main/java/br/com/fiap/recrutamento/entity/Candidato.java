package br.com.fiap.recrutamento.entity;

import br.com.fiap.recrutamento.dto.CandidatoCreateOrUpdateDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_CANDIDATO")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @Column
    private String curriculo;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column
    private String cargo;

    public Candidato(){}

    public Candidato(CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO) {
        this.nome = candidatoCreateOrUpdateDTO.getNome();
        this.dataNascimento = candidatoCreateOrUpdateDTO.getDataNascimento();
        this.curriculo = candidatoCreateOrUpdateDTO.getCurriculo();
        this.email = candidatoCreateOrUpdateDTO.getEmail();
        this.telefone = candidatoCreateOrUpdateDTO.getTelefone();
        this.cargo = candidatoCreateOrUpdateDTO.getCargo();
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
}
