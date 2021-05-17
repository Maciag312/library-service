package pwr.awt.demo.domain.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pwr.awt.demo.domain.user.User;

import java.time.LocalDate;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRental {
    long id;
    User user;
    Book book;
    LocalDate rentalDate;
    LocalDate dateToReturn;
    LocalDate dateOfReturn;
    BookRentalStatus status;
    double penalty;
}
