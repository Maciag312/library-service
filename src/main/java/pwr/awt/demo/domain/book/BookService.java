package pwr.awt.demo.domain.book;


import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    void add(Book book);
    void rentBook(int book_id, String email) throws ChangeSetPersister.NotFoundException;
    List<BookRental> getAllBooksRented(String email);
    void returnBook(long book_id, String email) throws ChangeSetPersister.NotFoundException;
}
