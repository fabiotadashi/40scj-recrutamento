package br.com.fiap.recrutamento.service;

import br.com.fiap.recrutamento.dto.CandidatoCreateOrUpdateDTO;
import br.com.fiap.recrutamento.dto.CandidatoDTO;

import java.time.LocalDate;

public class EstagioValidatorImpl implements EstagioValidator {

    private int idade;

    public EstagioValidatorImpl(int idade){
        this.idade = idade;
    }

    @Override
    public CandidatoCreateOrUpdateDTO definirEstagiario(CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO) {
        if(candidatoCreateOrUpdateDTO.getDataNascimento().isAfter(LocalDate.now().minusYears(idade))){
            candidatoCreateOrUpdateDTO.setCargo("Estagiário");
        }
        return candidatoCreateOrUpdateDTO;
    }

}
