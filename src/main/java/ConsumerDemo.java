import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo implements LifecycleManager{

    private static Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());
    private KafkaConsumer<String, Tweet> Consumer;
    private boolean isConsuming = false;
    private Thread ConsumerThread;

    public void start() {
        // Criar as propriedades do consumidor
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TweetDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumer_demo");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Criar o consumidor
        Consumer = new KafkaConsumer<String, Tweet>(properties);
        // Subscrever o consumidor para o nosso(s) tópico(s)
        Consumer.subscribe(Collections.singleton("twitter-topic"));
        //set control variable
        isConsuming = true;

        // Ler as mensagens
        ConsumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isConsuming) {
                    ConsumerRecords<String, Tweet> poll = Consumer.poll(Duration.ofMillis(10000));
                    for (ConsumerRecord record : poll) {
                        logger.info(record.topic() + " - " + record.partition() + " - " + record.value());
                    }
                }
            }
        });
        ConsumerThread.start();
    }

    public void stop() {
        this.isConsuming = false;
    }
}
