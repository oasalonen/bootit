package bootit.incident

import bootit.messenger.Messenger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController class IncidentController {

    @Autowired
    lateinit var repository: IncidentRepository

    @Autowired
    lateinit var messenger: Messenger

    @RequestMapping(value = "/incidents", method = [RequestMethod.POST])
    fun addIncident(@RequestParam(value = "what", defaultValue = "") what: String) : ResponseEntity<Incident> {
        val incident = repository.save(Incident(what));
        messenger.send("Added new Incident");
        return ResponseEntity(incident, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/incidents", method = [RequestMethod.GET])
    fun getIncidents(@RequestParam(value = "like", defaultValue = "") like: String) = when (like) {
            "" -> repository.findAll()
            else -> repository.findAll().filter { it.what.contains(like) }
    }
}