package bootit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableAutoConfiguration(exclude=RabbitAutoConfiguration.class)
@EnableCaching
public class BootitApplication {

    public static void main(String[] args) {
        //System.exit(1);
        SpringApplication.run(BootitApplication.class, args);
    }

}
