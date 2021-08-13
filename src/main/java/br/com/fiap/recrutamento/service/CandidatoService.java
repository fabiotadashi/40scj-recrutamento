package br.com.fiap.recrutamento.service;

import br.com.fiap.recrutamento.dto.CandidatoContatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoCreateOrUpdateDTO;
import br.com.fiap.recrutamento.dto.CandidatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoSimpleDTO;

import java.util.List;

public interface CandidatoService {

    List<CandidatoSimpleDTO> getCandidatos(String name);
    CandidatoDTO getCandidatoById(Long id);
    CandidatoDTO create(CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO);
    CandidatoDTO update(Long id, CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO);
    CandidatoDTO updateContato(Long id, CandidatoContatoDTO candidatoContatoDTO);
    void delete(Long id);

}
