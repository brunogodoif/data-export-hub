package br.com.brunogodoif.dataexporthub.datastorage.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Log4j2
@RequiredArgsConstructor
@Service
public class StringProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message, String key, String headerKey, String headerValue) {
        try {
            // Criar um cabeçalho personalizado
            Header customHeader = new RecordHeader(headerKey, headerValue.getBytes());

            // Criar um ProducerRecord com a chave, a mensagem e os cabeçalhos
            ProducerRecord<String, String> record = new ProducerRecord<>("str-topic", key, message);
            record.headers().add(customHeader);

            // Enviar a mensagem para o Kafka e obter o resultao
            SendResult<String, String> result = kafkaTemplate.send(record).get();

            // Logar o sucesso do envio e os metadados do registro
            log.info("Send message with success {}", message);
            log.info("Partition {}, Offset {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        } catch (InterruptedException | ExecutionException e) {
            // Logar o erro caso ocorra uma exceção ao enviar a mensagem
            log.error("Error sending message: {}", e.getMessage());
        }
    }
}