package pwr.awt.demo.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pwr.awt.demo.domain.book.pentalties.PentaltiesCalculator;
import pwr.awt.demo.domain.user.User;
import pwr.awt.demo.domain.user.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookRentalRepository bookRentalRepository;
    private final UserRepository userRepository;
    private final PentaltiesCalculator pentaltiesCalculator;

    @Override
    public List<Book> getAll() {
       return bookRepository.getAllBooks();
    }

    @Override
    public void add(Book book) {
        bookRepository.add(book);
    }

    @Override
    public void rentBook(int book_id, String email) throws ChangeSetPersister.NotFoundException, IllegalArgumentException {
        LocalDate date = LocalDate.now();
        LocalDate dt = LocalDate.now().plusDays(14);
        User user = userRepository.findByEmail(email).get();
        Optional<BookRental> rentedBook = bookRentalRepository.getAllByUser(user).stream().findAny();
        if(rentedBook.isPresent())
            throw new IllegalArgumentException("Book is already rented");
        Book book = bookRepository.getBookByID(book_id);
        if(!book.removeFromStack()){
            throw new IllegalArgumentException("No book with given id is found");
        }
        bookRepository.save(book);
        BookRental bookRental = new BookRental(0, user, book, date, dt, null, BookRentalStatus.RENTED, 0d);
        bookRentalRepository.add(bookRental);
    }

    @Override
    public List<BookRental> getAllBooksRented(String email) {
        User user = userRepository.findByEmail(email).get();
        List<BookRental> bookRentals =  bookRentalRepository.getAllByUser(user);
        return updatePenalties(bookRentals);
    }

    List<BookRental> updatePenalties(List<BookRental> bookRentals){
        for (BookRental bookRental : bookRentals) {
            if(bookRental.status==BookRentalStatus.RENTED) {
                bookRental.penalty = pentaltiesCalculator.calculatePenalty(bookRental.dateToReturn, LocalDate.now());
            }
        }
        return bookRentalRepository.save(bookRentals);
    }

    @Override
    public void returnBook(long book_id, String email) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findByEmail(email).get();
        Book book = bookRepository.getBookByID((int) book_id);
        BookRental bookRental = bookRentalRepository.findByBookAndUser(book, user);
        bookRental.status = BookRentalStatus.RETURNED;
        List<BookRental> rentals = new ArrayList<>();
        rentals.add(bookRental);
        bookRentalRepository.save(rentals);
    }


}
