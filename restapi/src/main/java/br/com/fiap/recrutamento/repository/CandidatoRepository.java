package br.com.fiap.recrutamento.repository;

import br.com.fiap.recrutamento.entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    List<Candidato> findAllByNomeStartsWith(String nome);

    @Query("select c from Candidato c where c.nome like %:nome")
    List<Candidato> buscarPorNome(String nome);

}
