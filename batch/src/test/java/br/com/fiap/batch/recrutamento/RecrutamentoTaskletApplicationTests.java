package br.com.fiap.batch.recrutamento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
		RecrutamentoChunkApplication.class,
		BatchTestConfiguration.class
})
class RecrutamentoTaskletApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private Job job;

	@Autowired
	private DataSource dataSource;

	@Test
	public void shouldInsert3CandidatosNoBancoLendoDoArquivo() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, SQLException {
		JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher()
				.run(
						job,
						jobLauncherTestUtils.getUniqueJobParameters()
				);

		assertNotNull(jobExecution);
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

		await().atMost(10, TimeUnit.SECONDS)
				.until(() -> {
					ResultSet resultSet = dataSource.getConnection()
							.prepareStatement("select count(*) from TB_CANDIDATO")
							.executeQuery();

					resultSet.last();
					int count = resultSet.getInt(1);
					return count == 3;
				});
	}

}
