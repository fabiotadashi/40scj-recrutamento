package br.com.fiap.recrutamento.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("debug")
@ConditionalOnProperty(value = "fiap.debug", havingValue = "true")
public class DebugController {

    private final Environment environment;

    public DebugController(Environment environment){
        this.environment = environment;
    }

    @GetMapping
    public String getProperty(@RequestParam String property){
        return environment.getProperty(property);
    }

}
