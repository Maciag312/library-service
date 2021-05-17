package pwr.awt.demo.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import pwr.awt.demo.domain.book.Book;
import pwr.awt.demo.domain.book.BookRental;
import pwr.awt.demo.domain.book.BookRentalRepository;
import pwr.awt.demo.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JpaBookRentalRepositoryImpl implements BookRentalRepository {

    private final JpaBookRentalRepo jpaBookRentalRepository;

    @Override
    public List<BookRental> getAllByUser(User user) {
        return jpaBookRentalRepository.findAllByUser(UserTuple.from(user)).stream()
                .map(BookRentalTuple::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void add(BookRental bookRental) {
        jpaBookRentalRepository.save(BookRentalTuple.from(bookRental));
    }


    public BookRental findByBookAndUser(Book book, User user) {
        return jpaBookRentalRepository.findByBookAndUser(BookTuple.from(book), UserTuple.from(user)).toDomain();
    }

    @Override
    public List<BookRental> save(List<BookRental> bookRentals) {
        List<BookRentalTuple> tuples = bookRentals.stream().map(BookRentalTuple::from).collect(Collectors.toList());
        return jpaBookRentalRepository.saveAll(tuples).stream().map(BookRentalTuple::toDomain).collect(Collectors.toList());
    }

    public interface JpaBookRentalRepo extends JpaRepository<BookRentalTuple, Long> {
        List<BookRentalTuple> findAllByUser(UserTuple user);
        BookRentalTuple findByBookAndUser(BookTuple book, UserTuple user);

    }
}
