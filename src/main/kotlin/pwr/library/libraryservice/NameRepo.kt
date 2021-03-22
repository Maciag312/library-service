package pwr.library.libraryservice

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NameRepo : CrudRepository<Name, Long> {
    fun findByName(name: String): Name?
}