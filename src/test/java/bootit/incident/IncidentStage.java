package bootit.incident;

import bootit.messenger.Messenger;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@JGivenStage
public class IncidentStage extends Stage<IncidentStage> {

    @Autowired
    private IncidentController controller;

    @MockBean
    private Messenger messenger;

    @Autowired
    private IncidentRepository repository;

    public IncidentStage get(@Quoted String path) throws Exception {
        controller.getIncident(0);
        return this;
    }

    public IncidentStage incident_is_in_database(String what) {
        controller.addIncident(what);
        return this;
    }
}
