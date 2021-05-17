package pwr.awt.demo.domain.book;

import pwr.awt.demo.domain.user.User;

import java.util.List;

public interface BookRentalRepository {
    List<BookRental> getAllByUser(User user);
    void add(BookRental bookRental);
    BookRental findByBookAndUser(Book book, User user);
    List<BookRental> save(List<BookRental> bookRentals);
}
