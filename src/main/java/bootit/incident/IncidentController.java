package bootit.incident;

import bootit.messenger.Messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncidentController {

    @Autowired
    private IncidentRepository repository;

    @Autowired
    private Messenger messenger;

    @RequestMapping(value = "/incidents", method = RequestMethod.POST)
    public ResponseEntity<Incident> addIncident(@RequestParam(value="what", defaultValue="") String what) {
        messenger.send("Added new Incident");
        Incident incident = repository.save(new Incident(what));
        return new ResponseEntity<Incident>(incident, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/incidents", method = RequestMethod.GET)
    public Iterable<Incident> getIncidents() {
        return repository.findAll();
    }
}
