package br.com.fiap.recrutamento.unit;

import br.com.fiap.recrutamento.controller.CandidatoController;
import br.com.fiap.recrutamento.dto.CandidatoSimpleDTO;
import br.com.fiap.recrutamento.service.CandidatoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CandidatoControllerTest {

    @Test
    public void deveRetornarListaDeCandidatos(){
        CandidatoService candidatoService = Mockito.mock(CandidatoService.class);
        List<CandidatoSimpleDTO> candidatoRetornoMock = Arrays.asList(new CandidatoSimpleDTO(), new CandidatoSimpleDTO());

        Mockito.when(candidatoService.getCandidatos(null)).thenReturn(candidatoRetornoMock);

        CandidatoController candidatoController = new CandidatoController(candidatoService);
        List<CandidatoSimpleDTO> candidatos = candidatoController.getCandidatos(null);

        assertThat(candidatos.size(), equalTo(2));
    }

}
