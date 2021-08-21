package br.com.fiap.recrutamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RecrutamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecrutamentoApplication.class, args);
	}

}
