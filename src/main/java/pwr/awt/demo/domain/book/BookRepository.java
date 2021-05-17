package pwr.awt.demo.domain.book;

import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    void add(Book book);
    Book save(Book book);
    Book getBookByID(int bookID) throws ChangeSetPersister.NotFoundException;
}
