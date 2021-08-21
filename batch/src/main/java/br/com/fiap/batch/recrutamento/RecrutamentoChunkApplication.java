package br.com.fiap.batch.recrutamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.avro.builder.AvroItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@SpringBootApplication
@EnableBatchProcessing
public class RecrutamentoChunkApplication {

    Logger logger = LoggerFactory.getLogger(RecrutamentoChunkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RecrutamentoChunkApplication.class, args);
    }

    @Bean
    public ItemReader<Candidato> itemReader(@Value("${file.candidatos}") Resource resource){
        return new FlatFileItemReaderBuilder<Candidato>()
                .name("Candito CSV Reader")
                .resource(resource)
                .targetType(Candidato.class)
                .delimited().delimiter(";").names("nome", "cargo")
                .build();
    }

    @Bean
    public ItemProcessor<Candidato, Candidato> itemProcessor(){
        return candidato -> {
          // Regra de negócio
            candidato.setNome(candidato.getNome().toUpperCase());
            candidato.setCargo(candidato.getCargo().replace(".",""));
            return candidato;
        };
    }

    @Bean
    public ItemWriter<Candidato> itemWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Candidato>()
                .dataSource(dataSource)
                .sql("insert into TB_CANDIDATO (nome, cargo) values (:nome, :cargo)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step step(
            StepBuilderFactory stepBuilderFactory,
            ItemReader<Candidato> itemReader,
            ItemProcessor<Candidato,Candidato> itemProcessor,
            ItemWriter<Candidato> itemWriter
    ){
        return stepBuilderFactory.get("Step file to DB")
                .<Candidato, Candidato>chunk(2)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   Step step){
        return jobBuilderFactory.get("Job process Candidato csv")
                .start(step)
                .build();
    }

}
