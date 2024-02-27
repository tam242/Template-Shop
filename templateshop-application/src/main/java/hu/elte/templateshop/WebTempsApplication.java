package hu.elte.templateshop;

import hu.elte.templateshop.services.EmailSenderService;
import hu.elte.templateshop.services.TestDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebTempsApplication {
	@Autowired
	private TestDataGenerator testDataGenerator;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(WebTempsApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			testDataGenerator.createTestData();
			//emailSenderService.sendMessageWithAttachment("w4e4v5@inf.elte.hu");
		};
	}
}
