package bootit.incident;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class ThenIncidents extends Stage<ThenIncidents> {

    @ExpectedScenarioState
    ResponseEntity<Incident> incident;

    public ThenIncidents status_code_is_$(int status){
        assertEquals(status, incident.getStatusCodeValue());
        return this;
    }

    public ThenIncidents incident_id_is_$(long id) {
        assertEquals(id, incident.getBody().getId());
        return this;
    }

}
