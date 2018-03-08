package bootit.incident

import org.springframework.data.repository.CrudRepository

interface IncidentRepository : CrudRepository<Incident, Long>