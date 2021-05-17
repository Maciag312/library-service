package pwr.awt.demo.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pwr.awt.demo.domain.book.Book;
import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="BOOK")
public class BookTuple {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name="book_seq", sequenceName="book_seq", allocationSize=1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;
    private String author;
    private String imageURL;
    private String blurb;
    private int available;
    private int total;


    static BookTuple from(Book book) {
        return BookTuple.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .available(book.getAvailable())
                .imageURL(book.getImageURL())
                .blurb(book.getBlurb())
                .total(book.getTotal())
                .build();
    }

    Book toDomain(){
        return Book.builder().
                id(getId())
                .title(getTitle())
                .author(getAuthor())
                .available(getAvailable())
                .imageURL(getImageURL())
                .blurb(getBlurb())
                .total(getTotal())
                .build();
    }
}
