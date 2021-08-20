package br.com.fiap.recrutamento.repository;

import br.com.fiap.recrutamento.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findFirstByNome(String nome);

}
