package bootit.messenger;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class MqComponent implements Messenger {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    Queue queue() {
        return new Queue("bootit", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("bootit-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("bootit");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("bootit");
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                System.out.println("AMQP: " + message);
            }
        };
    }

    @Override
    public void send(String message) {
        rabbitTemplate.convertAndSend("bootit", message);
    }
}
