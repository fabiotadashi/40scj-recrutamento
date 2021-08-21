//package br.com.fiap.batch.recrutamento;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Scope;
//
//import java.io.File;
//import java.nio.file.Paths;
//
//@SpringBootApplication
//@EnableBatchProcessing
//public class RecrutamentoTaskletApplication {
//
//    Logger logger = LoggerFactory.getLogger(RecrutamentoTaskletApplication.class);
//
//    public static void main(String[] args) {
//        SpringApplication.run(RecrutamentoTaskletApplication.class, args);
//    }
//
//    @Bean
//    public Tasklet tasklet(@Value("${file.path}") String filePath) {
//        return (contribution, chunkContext) -> {
//            // logica de negocio
//            File file = Paths.get(filePath).toFile();
//            if (file.delete()) {
//                logger.info("Arquivo deletado");
//            } else {
//                logger.warn("Arquivo inexistente ou não foi possível deletar");
//            }
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//    @Scope("prototype")
//    public Step step(Tasklet tasklet,
//                     StepBuilderFactory stepBuilderFactory){
//        return stepBuilderFactory.get("Delete file step")
//                .tasklet(tasklet)
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    @Bean
//    public Job job(JobBuilderFactory jobBuilderFactory,
//                   Step step){
//        return jobBuilderFactory.get("Delete file Job")
//                .start(step)
//                .build();
//    }
//
//}
