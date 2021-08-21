package br.com.fiap.recrutamento.controller;

import br.com.fiap.recrutamento.dto.AuthDTO;
import br.com.fiap.recrutamento.dto.CreateUserDTO;
import br.com.fiap.recrutamento.dto.JwtDTO;
import br.com.fiap.recrutamento.dto.UserDTO;
import br.com.fiap.recrutamento.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private UserService userService;

    public UsuarioController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }

    @PostMapping("login")
    public JwtDTO login(@RequestBody AuthDTO authDTO){
        return userService.login(authDTO);
    }

}
