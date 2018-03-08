package bootit.incident

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Incident(
        val what: String,
        @Id @GeneratedValue val id : Long,
        val timestamp: Long) {

    constructor(what: String) : this(what, -1, System.currentTimeMillis())
    constructor() : this("")

}