package bootit.incident

import bootit.messenger.Messenger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class IncidentController {

    @Autowired
    lateinit var repository: IncidentRepository

    @Autowired
    lateinit var messenger: Messenger

    @PostMapping("/incidents")
    fun addIncident(@RequestParam(value = "what", defaultValue = "") what: String) : ResponseEntity<Incident> {
        val incident = repository.save(Incident(what));
        messenger.send("Added new Incident");
        return ResponseEntity(incident, HttpStatus.CREATED);
    }

    @GetMapping("/incidents")
    fun getIncidents(@RequestParam(value = "like", defaultValue = "") like: String) = when (like) {
            "" -> repository.findAll()
            else -> repository.findAll().filter { it.what.contains(like) }
    }

    @GetMapping("/incidents/{id}")
    fun getIncident(@PathVariable id: Long) =
            repository.findById(id)
                    .map { ResponseEntity.ok(it) }
                    .orElse( ResponseEntity(HttpStatus.NOT_FOUND) )
}