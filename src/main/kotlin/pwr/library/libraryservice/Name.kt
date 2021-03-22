package pwr.library.libraryservice

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Name(
        var name: String,
        @Id @GeneratedValue var id: Long? = null)
