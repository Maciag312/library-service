package pwr.library.libraryservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class GreetingsController constructor(
        val nameRepo: NameRepo){

    @GetMapping("/hello")
    fun greet(): String {
        return "Greetings" + nameRepo.findAll();
    }

    @PostMapping("/name/{name}")
    fun name(@PathVariable name: String) {
        nameRepo.save(Name(name))
    }

}