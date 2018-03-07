package bootit.messenger;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class MqComponent implements Messenger {

    private final String queueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    protected MqComponent(@Value("${bootit.mq.queue-name}") String queueName){
        this.queueName = queueName;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("bootit-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(queueName);
    }

    @Override
    public void send(String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @RabbitListener(queues = "${bootit.mq.queue-name}")
    @Component
    public class Receiver {
        @RabbitHandler
        public void receive(String message) {
            System.out.println("AMQP: " + message);
        }
    }
}
