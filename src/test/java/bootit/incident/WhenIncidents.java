package bootit.incident;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@JGivenStage
public class WhenIncidents extends Stage<WhenIncidents> {

    @Autowired
    private IncidentController controller;

    @ProvidedScenarioState
    ResponseEntity<Incident> incident;

    public WhenIncidents requesting_incident_with_id(long id) {
        incident = controller.getIncident(id);
        return this;
    }

}
