package br.com.fiap.recrutamento.security;

import com.sun.net.httpserver.Headers;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String BEARER = "Bearer ";
    private Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailService jwtUserDetailService;

    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil,
                            JwtUserDetailService jwtUserDetailService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailService = jwtUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeaderToken = request.getHeader("Authorization");
        String nome = null;
        String jwtToken;

        if (authHeaderToken != null && authHeaderToken.startsWith(BEARER)) {
            jwtToken = authHeaderToken.replace(BEARER, "");

            try {
                nome = jwtTokenUtil.getUserFromToken(jwtToken);
            } catch (IllegalArgumentException illegalArgumentException) {
                logger.info("Erro ao fazer parse do token");
            } catch (ExpiredJwtException expiredJwtException) {
                logger.info("Token expirado");
            }
        } else {
            logger.info("Token não encontrado ou fora do padrão Bearer");
        }

        if(nome != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails =  jwtUserDetailService.loadUserByUsername(nome);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

}
