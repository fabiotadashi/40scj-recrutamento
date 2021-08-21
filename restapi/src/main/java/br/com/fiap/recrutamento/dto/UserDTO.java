package br.com.fiap.recrutamento.dto;

import br.com.fiap.recrutamento.entity.Usuario;

public class UserDTO {

    private long id;
    private String nome;

    public UserDTO(){}

    public UserDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
