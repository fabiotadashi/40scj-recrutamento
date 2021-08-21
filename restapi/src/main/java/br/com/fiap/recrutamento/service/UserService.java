package br.com.fiap.recrutamento.service;

import br.com.fiap.recrutamento.dto.AuthDTO;
import br.com.fiap.recrutamento.dto.CreateUserDTO;
import br.com.fiap.recrutamento.dto.JwtDTO;
import br.com.fiap.recrutamento.dto.UserDTO;

public interface UserService {

    UserDTO create(CreateUserDTO createUserDTO);
    JwtDTO login(AuthDTO authDTO);

}
