package bootit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude=RabbitAutoConfiguration.class)
public class BootitApplication {

    public static void main(String[] args) {
        //System.exit(1);
        SpringApplication.run(BootitApplication.class, args);
    }

}
