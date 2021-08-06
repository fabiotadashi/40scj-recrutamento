package br.com.fiap.recrutamento.controller;

import br.com.fiap.recrutamento.dto.CandidatoContatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoCreateOrUpdateDTO;
import br.com.fiap.recrutamento.dto.CandidatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoSimpleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("candidatos")
public class CandidatoController {

    private List<CandidatoDTO> listaCandidatos = new ArrayList<>();

    public CandidatoController() {
        CandidatoDTO candidatoDTO = new CandidatoDTO();
        candidatoDTO.setId(1L);
        candidatoDTO.setNome("Fabio Tadashi");
        candidatoDTO.setCargo("Arquiteto de Soluções");
        candidatoDTO.setCurriculo("www.linkedin.com/fabiotadashi");
        candidatoDTO.setEmail("fabio@fiap.com");
        candidatoDTO.setTelefone("921382198");
        candidatoDTO.setDataNascimento(LocalDate.of(1986, 6, 15));

        CandidatoDTO candidatoDTO2 = new CandidatoDTO();
        candidatoDTO2.setId(2L);
        candidatoDTO2.setNome("Joao");
        candidatoDTO2.setCargo("Arquiteto de Software");
        candidatoDTO2.setCurriculo("www.linkedin.com/joao");
        candidatoDTO2.setEmail("joao@fiap.com");
        candidatoDTO2.setTelefone("134131221");
        candidatoDTO2.setDataNascimento(LocalDate.of(2000, 1, 1));

        listaCandidatos.add(candidatoDTO);
        listaCandidatos.add(candidatoDTO2);
    }

    @GetMapping
    public List<CandidatoSimpleDTO> getCandidatos(
            @RequestParam(name = "nome", required = false) String nome
    ) {
        return listaCandidatos.stream()
                .filter(dto -> nome == null || dto.getNome().startsWith(nome))
                .map(CandidatoSimpleDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CandidatoDTO getCandidatoById(
            @PathVariable Long id
    ) {
        return getCandidatoDTOById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidatoDTO createCandidato(
            @RequestBody CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO
    ) {
        CandidatoDTO candidatoDTO = new CandidatoDTO(candidatoCreateOrUpdateDTO);
        candidatoDTO.setId(listaCandidatos.size() + 1L);
        listaCandidatos.add(candidatoDTO);
        return candidatoDTO;
    }

    @PutMapping("{id}")
    public CandidatoDTO updateCandidato(
            @RequestBody CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO,
            @PathVariable Long id
    ) {
        CandidatoDTO candidatoDTO = getCandidatoDTOById(id);
        candidatoDTO.setNome(candidatoCreateOrUpdateDTO.getNome());
        candidatoDTO.setTelefone(candidatoCreateOrUpdateDTO.getTelefone());
        candidatoDTO.setDataNascimento(candidatoCreateOrUpdateDTO.getDataNascimento());
        candidatoDTO.setEmail(candidatoCreateOrUpdateDTO.getEmail());
        candidatoDTO.setCargo(candidatoCreateOrUpdateDTO.getCargo());
        candidatoDTO.setCurriculo(candidatoCreateOrUpdateDTO.getCurriculo());
        return candidatoDTO;
    }

    @PatchMapping("{id}")
    public CandidatoDTO updateContato(
            @RequestBody CandidatoContatoDTO candidatoContatoDTO,
            @PathVariable Long id
    ) {
        CandidatoDTO candidatoDTO = getCandidatoDTOById(id);
        candidatoDTO.setEmail(candidatoContatoDTO.getEmail());
        candidatoDTO.setTelefone(candidatoContatoDTO.getTelefone());
        return candidatoDTO;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidato(
            @PathVariable Long id
    ) {
        CandidatoDTO candidatoDTO = getCandidatoDTOById(id);
        listaCandidatos.remove(candidatoDTO);
    }

    private CandidatoDTO getCandidatoDTOById(Long id) {
        return listaCandidatos.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato nao encontrado"));
    }

}
