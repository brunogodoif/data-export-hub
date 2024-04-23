package br.com.brunogodoif.dataexporthub.converterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class DataConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataConverterApplication.class, args);
    }

}
