package br.com.fiap.batch.recrutamento;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchTestConfiguration {
  
   @Bean
   public JobLauncherTestUtils jobLauncherTestUtils() {
       return new JobLauncherTestUtils();
   }

}
