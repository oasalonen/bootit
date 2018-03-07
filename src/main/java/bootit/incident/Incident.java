package bootit.incident;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String what;
    private Long timestamp;

    protected Incident() {
    }

    public Incident(String what) {
        this.what = what;
        this.timestamp = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public String getWhat() {
        return what;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Incident[id=%d, what=%s, ts=%d]", id, what, timestamp);
    }
}
