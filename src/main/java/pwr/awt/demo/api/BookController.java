package pwr.awt.demo.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pwr.awt.demo.domain.book.Book;
import pwr.awt.demo.domain.book.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Api( tags = "Book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }

    @PostMapping
    public void add(@RequestBody Book book){
        bookService.add(book);
    }

}
