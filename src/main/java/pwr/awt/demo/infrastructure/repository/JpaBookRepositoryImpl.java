package pwr.awt.demo.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import pwr.awt.demo.domain.book.Book;
import pwr.awt.demo.domain.book.BookRepository;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JpaBookRepositoryImpl implements BookRepository {

    private final JpaBookRepo jpaBookRepo;

    public List<Book> getAllBooks(){
        return jpaBookRepo.findAll().stream().map(BookTuple::toDomain).collect(Collectors.toList());
    }

    @Override
    public void add(Book book) {
        BookTuple bookTuple = BookTuple.from(book);
        jpaBookRepo.save(bookTuple);
    }

    @Override
    public Book save(Book book) {
        BookTuple bookTuple = BookTuple.from(book);
        return jpaBookRepo.save(bookTuple).toDomain();
    }

    @Override
    public Book getBookByID(int bookID) throws ChangeSetPersister.NotFoundException {

        return jpaBookRepo.findById((long) bookID).orElseThrow(ChangeSetPersister.NotFoundException::new).toDomain();
    }

    public interface JpaBookRepo extends JpaRepository<BookTuple, Long> {
    }

}
