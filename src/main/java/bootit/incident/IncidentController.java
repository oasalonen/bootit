package bootit.incident;

import bootit.messenger.Messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncidentController {

    @Autowired
    private IncidentRepository repository;

    @Autowired
    private Messenger messenger;

    @RequestMapping("/incident")
    public Iterable<Incident> incident(@RequestParam(value="what", defaultValue="") String what) {
        messenger.send("Added new Incident");
        repository.save(new Incident(what));
        return repository.findAll();
    }

}
