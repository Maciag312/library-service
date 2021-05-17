package pwr.awt.demo.api.dto;

import lombok.Builder;
import lombok.Getter;
import pwr.awt.demo.domain.book.BookRental;
import pwr.awt.demo.domain.book.BookRentalStatus;

import java.time.LocalDate;

@Builder
@Getter
public class BookRentDto {
    private long id;
    private String title;
    private String author;
    private String imageURL;
    private String blurb;
    LocalDate dateOfReturn;
    LocalDate dateToReturn;
    LocalDate rentalDate;
    double penalty;
    BookRentalStatus status;


    public static BookRentDto fromBookRental(BookRental bookRental){
       return BookRentDto.builder()
                .id(bookRental.getId())
                .author(bookRental.getBook().getAuthor())
                .title(bookRental.getBook().getTitle())
                .imageURL(bookRental.getBook().getImageURL())
                .blurb(bookRental.getBook().getBlurb())
                .dateOfReturn(bookRental.getDateOfReturn())
                .dateToReturn(bookRental.getDateToReturn())
                .rentalDate(bookRental.getRentalDate())
               .penalty(bookRental.getPenalty())
               .status(bookRental.getStatus())
               .build();
    }
}
