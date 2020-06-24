package ng.com.gness.cardschemeconsumer;

import ng.com.gness.cardschemeconsumer.model.Card;
import ng.com.gness.cardschemeconsumer.pojo.CardDTO;
import ng.com.gness.cardschemeconsumer.services.CardService;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.sql.SQLException;

@SpringBootApplication
public class CardschemeConsumerApplication {

    private final Logger logger = LoggerFactory.getLogger(CardschemeConsumerApplication.class);
    private final TaskExecutor exec = new SimpleAsyncTaskExecutor();
    @Autowired
    private CardService cardService;

    public static void main(String[] args) {
        SpringApplication.run(CardschemeConsumerApplication.class, args).close();
    }

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @KafkaListener(id = "fooGroup", topics = "com.ng.vela.even.card_verified")
    public void listen(CardDTO cardDTO) {
        logger.info("Received: " + cardDTO);
        Card card = new Card(cardDTO);
        cardService.save(card);
        this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            System.out.println("Hit Enter to terminate...");
            System.in.read();
        };
    }

    //a Spring bean for an in-memory H2 database — and expose it via a TCP port
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

}