package br.com.brunogodoif.dataexporthub.datastorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class DataStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataStorageApplication.class, args);
	}

}
