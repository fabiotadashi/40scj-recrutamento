package br.com.fiap.recrutamento.service;

import br.com.fiap.recrutamento.dto.CandidatoContatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoCreateOrUpdateDTO;
import br.com.fiap.recrutamento.dto.CandidatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoSimpleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    @Override
    public List<CandidatoSimpleDTO> getCandidatos(String name) {
        return null;
    }

    @Override
    public CandidatoDTO getCandidatoById(Long id) {
        return null;
    }

    @Override
    public CandidatoDTO create(CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO) {
        return null;
    }

    @Override
    public CandidatoDTO update(Long id, CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO) {
        return null;
    }

    @Override
    public CandidatoDTO updateContato(Long id, CandidatoContatoDTO candidatoContatoDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
