package br.com.brunogodoif.dataexporthub.dataconverter.listeners;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

//    @StrConsumerCustomListener(groupId = "group-1", topics = "str-topic")
//    public void create(String message, Acknowledgment ack) {
//        log.info("CREATE ::: Receive message {}", message);
//        log.info("CREATE ::: Message acknowledged and processed successfully");
//    }

    @KafkaListener(topics = "str-topic", groupId = "group-1", containerFactory = "strContainerFactory")
    public void create(String message, Acknowledgment acknowledgment) {
        log.info("CREATE ::: Receive message {}", message);

        // Processar a mensagem...
        processarMensagem(message);

        // Confirmar o processamento da mensagem ao Kafka
        acknowledgment.acknowledge();

        log.info("CREATE ::: Message acknowledged and processed successfully");
    }

    private void processarMensagem(String mensagem) {
        // Implemente aqui a lógica de processamento da mensagem
        log.info("Mensagem processada: {}", mensagem);
    }

//    @StrConsumerCustomListener(groupId = "group-1")
//    public void log(String message) {
//        log.info("LOG ::: Receive message {}", message);
//    }
//
//    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
//    public void history(String message) {
//        log.info("HISTORY ::: Receive message {}", message);
//    }

}