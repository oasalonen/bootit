package bootit.incident;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class GivenIncidents extends Stage<GivenIncidents> {

    @Autowired
    private IncidentRepository repository;

    @BeforeStage
    public void setup() {
        repository.deleteAll();
    }

    public GivenIncidents $_incidents(int count) {
        for (long i = 0; i < count; i++) {
            given().incident_with_id(i);
        }
        return this;
    }

    public GivenIncidents incident_with_id(long id) {
        Incident incident = new Incident(String.valueOf(id));
        repository.save(incident);
        return this;
    }

}
