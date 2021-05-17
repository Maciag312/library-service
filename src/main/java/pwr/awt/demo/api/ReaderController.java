package pwr.awt.demo.api;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.awt.demo.api.dto.BookRentDto;
import pwr.awt.demo.domain.book.BookService;
import pwr.awt.demo.domain.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reader")
@RequiredArgsConstructor
@Api( tags = "Reader")
public class ReaderController {

    private final BookService bookService;
    private final UserService userService;

    @GetMapping(value = "/rent/{book_id}")
    public ResponseEntity rentBook(@PathVariable int book_id){
        try {
            bookService.rentBook(book_id, userService.getUserDetails().getEmail());
        } catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/rent/books")
    public List<BookRentDto> getRentedBooks(){
        return bookService.getAllBooksRented(userService.getUserDetails().getEmail()).stream()
                .map(BookRentDto::fromBookRental).collect(Collectors.toList());
    }

    @PostMapping(value = "/return/{book_id}")
    public ResponseEntity returnBook(@PathVariable long book_id){
        try {
            bookService.returnBook(book_id, userService.getUserDetails().getEmail());
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
