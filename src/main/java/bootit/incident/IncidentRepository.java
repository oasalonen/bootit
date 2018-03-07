package bootit.incident;

import org.springframework.data.repository.CrudRepository;

public interface IncidentRepository extends CrudRepository<Incident, Long> {
}
