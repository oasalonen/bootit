package bootit.incident;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JGivenStage
public class GivenMockitoIncidents extends Stage<GivenMockitoIncidents> {

    @MockBean
    private IncidentRepository repository;

    private List<Incident> incidentList = new ArrayList<>();

    @BeforeStage
    public void setup() {
        incidentList.clear();
        Mockito.reset(repository);
    }

    public GivenMockitoIncidents $_incidents(int count) {
        for (long i = 0; i < count; i++) {
            given().incident_with_id(i);
        }
        return this;
    }

    public GivenMockitoIncidents incident_with_id(long id) {
        Incident incident = new Incident(String.valueOf(id), id, id);
        incidentList.add(incident);

        Mockito.when(repository.findById(id))
            .thenReturn(Optional.of(incident));

        Mockito.when(repository.findAll())
                .thenReturn(incidentList);
        return this;
    }

}
