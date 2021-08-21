package br.com.fiap.recrutamento.service;

import br.com.fiap.recrutamento.dto.AuthDTO;
import br.com.fiap.recrutamento.dto.CreateUserDTO;
import br.com.fiap.recrutamento.dto.JwtDTO;
import br.com.fiap.recrutamento.dto.UserDTO;
import br.com.fiap.recrutamento.entity.Usuario;
import br.com.fiap.recrutamento.repository.UsuarioRepository;
import br.com.fiap.recrutamento.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UsuarioRepository usuarioRepository,
                           AuthenticationManager authenticationManager,
                           JwtTokenUtil jwtTokenUtil) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDTO create(CreateUserDTO createUserDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(createUserDTO.getNome());
        usuario.setPassword(passwordEncoder.encode(createUserDTO.getSenha()));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UserDTO(usuarioSalvo);
    }

    @Override
    public JwtDTO login(AuthDTO authDTO) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                authDTO.getNome(),
                authDTO.getSenha()
        );
        try {
            authenticationManager.authenticate(authentication);
        } catch (DisabledException disabledException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário desabilitado");
        } catch (BadCredentialsException badCredentialsException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credencial inválida");
        }

        String token = jwtTokenUtil.generateToken(authDTO.getNome());

        JwtDTO jwtDTO = new JwtDTO();
        jwtDTO.setToken(token);

        return jwtDTO;
    }

}
