package br.com.fiap.recrutamento.configuration;

import br.com.fiap.recrutamento.service.EstagioValidator;
import br.com.fiap.recrutamento.service.EstagioValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public EstagioValidator estagioValidator(){
        return new EstagioValidatorImpl(18);
    }

}
