package asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import asw.reportWriter.kafka.KafkaProducer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		
		KafkaProducer kP = new KafkaProducer();
		
		kP.sendNewSuggestion(2);
		kP.send(KafkaProducer.NEW_SUGGESTION, "funciona");
	}
}