package pwr.awt.demo.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pwr.awt.demo.api.dto.UserDTO;
import pwr.awt.demo.domain.book.Book;

import java.util.List;

@RestController
@RequestMapping("/librarian/books")
@RequiredArgsConstructor
@Api( tags = "User")
public class LibrarianController {

}
