package br.com.fiap.recrutamento.service;

import br.com.fiap.recrutamento.dto.CandidatoContatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoCreateOrUpdateDTO;
import br.com.fiap.recrutamento.dto.CandidatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoSimpleDTO;
import br.com.fiap.recrutamento.entity.Candidato;
import br.com.fiap.recrutamento.repository.CandidatoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    private final EstagioValidator estagioValidator;
    private final CandidatoRepository candidatoRepository;

    public CandidatoServiceImpl(EstagioValidator estagioValidator, CandidatoRepository candidatoRepository){
        this.estagioValidator = estagioValidator;
        this.candidatoRepository = candidatoRepository;
    }

    @Override
    public List<CandidatoSimpleDTO> getCandidatos(String name) {
        List<Candidato> candidatoList;
        if(name == null){
            candidatoList = candidatoRepository.findAll();
        } else {
            candidatoList = candidatoRepository.findAllByNomeStartsWith(name);
        }

        return candidatoList.stream()
                .map(CandidatoSimpleDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CandidatoDTO getCandidatoById(Long id) {
        Candidato candidato = getCandidatoEntityById(id);
        return new CandidatoDTO(candidato);
    }

    @Override
    public CandidatoDTO create(CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO) {
        candidatoCreateOrUpdateDTO = estagioValidator.definirEstagiario(candidatoCreateOrUpdateDTO);

        Candidato candidato = new Candidato(candidatoCreateOrUpdateDTO);

        Candidato candidatoCriado = candidatoRepository.save(candidato);

        return new CandidatoDTO(candidatoCriado);
    }

    @Override
    public CandidatoDTO update(Long id, CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO) {
        Candidato candidato = getCandidatoEntityById(id);
        candidato.setNome(candidatoCreateOrUpdateDTO.getNome());
        candidato.setCargo(candidatoCreateOrUpdateDTO.getCargo());
        candidato.setCurriculo(candidatoCreateOrUpdateDTO.getCurriculo());
        candidato.setDataNascimento(candidatoCreateOrUpdateDTO.getDataNascimento());
        candidato.setEmail(candidatoCreateOrUpdateDTO.getEmail());
        candidato.setTelefone(candidatoCreateOrUpdateDTO.getTelefone());

        Candidato candidatoUpdate = candidatoRepository.save(candidato);
        return new CandidatoDTO(candidatoUpdate);
    }

    @Override
    public CandidatoDTO updateContato(Long id, CandidatoContatoDTO candidatoContatoDTO) {
        Candidato candidato = getCandidatoEntityById(id);
        candidato.setEmail(candidatoContatoDTO.getEmail());
        candidato.setTelefone(candidatoContatoDTO.getTelefone());

        Candidato candidatoUpdate = candidatoRepository.save(candidato);
        return new CandidatoDTO(candidatoUpdate);
    }

    @Override
    public void delete(Long id) {
        candidatoRepository.deleteById(id);
    }

    private Candidato getCandidatoEntityById(Long id) {
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
