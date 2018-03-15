package bootit.incident;

import bootit.BootitTestContext;
import com.tngtech.jgiven.integration.spring.SpringRuleScenarioTest;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;

@SpringBootTest( classes = {MockServletContext.class, BootitTestContext.class })
public class IncidentControllerTest extends SpringRuleScenarioTest<GivenMockitoIncidents, WhenIncidents, ThenIncidents> {

    @Test
    public void get_incident_by_id_returns_existing_incident() {
        given().$_incidents(3);
        when().requesting_incident_with_id(1);
        then().status_code_is_$(200)
                .and()
                .incident_id_is_$(1);
    }

    @Test
    public void get_incident_by_nonexisting_id_returns_not_found() {
        given().$_incidents(0);
        when().requesting_incident_with_id(1);
        then().status_code_is_$(404);
    }

}
