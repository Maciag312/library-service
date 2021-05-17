package pwr.awt.demo.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pwr.awt.demo.domain.book.BookRental;
import pwr.awt.demo.domain.book.BookRentalStatus;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="BOOK_RENTAL")
public class BookRentalTuple {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name="book_seq", sequenceName="book_seq", allocationSize=1)
    private Long id;

    LocalDate rentalDate;
    LocalDate dateToReturn;
    LocalDate dateOfReturn;
    BookRentalStatus status;
    double penalty;


    @OneToOne
    UserTuple user;

    @OneToOne
    BookTuple book;

    static BookRentalTuple from(BookRental rental) {
        return BookRentalTuple.builder()
                .id(rental.getId())
                .book(BookTuple.from(rental.getBook()))
                .dateOfReturn(rental.getDateOfReturn())
                .dateToReturn(rental.getDateToReturn())
                .rentalDate(rental.getRentalDate())
                .status(rental.getStatus())
                .user(UserTuple.from(rental.getUser()))
                .penalty(rental.getPenalty())
                .build();
    }

    BookRental toDomain(){
        return BookRental.builder()
                .id(id)
                .book(book.toDomain())
                .dateOfReturn(dateOfReturn)
                .dateToReturn(dateToReturn)
                .rentalDate(rentalDate)
                .status(status)
                .user(user.toDomain())
                .penalty(penalty)
                .build();
    }
}
