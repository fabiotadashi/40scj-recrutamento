package br.com.fiap.recrutamento.controller;

import br.com.fiap.recrutamento.dto.CandidatoContatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoCreateOrUpdateDTO;
import br.com.fiap.recrutamento.dto.CandidatoDTO;
import br.com.fiap.recrutamento.dto.CandidatoSimpleDTO;
import br.com.fiap.recrutamento.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("candidatos")
public class CandidatoController {

    private final CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping
    public List<CandidatoSimpleDTO> getCandidatos(
            @RequestParam(name = "nome", required = false) String nome
    ) {
        return candidatoService.getCandidatos(nome);
    }

    @GetMapping("{id}")
    public CandidatoDTO getCandidatoById(
            @PathVariable Long id
    ) {
        return candidatoService.getCandidatoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidatoDTO createCandidato(
            @RequestBody CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO
    ) {
        return candidatoService.create(candidatoCreateOrUpdateDTO);
    }

    @PutMapping("{id}")
    public CandidatoDTO updateCandidato(
            @RequestBody CandidatoCreateOrUpdateDTO candidatoCreateOrUpdateDTO,
            @PathVariable Long id
    ) {
        return candidatoService.update(id, candidatoCreateOrUpdateDTO);
    }

    @PatchMapping("{id}")
    public CandidatoDTO updateContato(
            @RequestBody CandidatoContatoDTO candidatoContatoDTO,
            @PathVariable Long id
    ) {
        return candidatoService.updateContato(id, candidatoContatoDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidato(
            @PathVariable Long id
    ) {
        candidatoService.delete(id);
    }

}
