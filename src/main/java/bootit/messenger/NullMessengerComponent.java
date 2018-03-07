package bootit.messenger;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class NullMessengerComponent implements Messenger {
    @Override
    public void send(String message) {
    }
}
