package pwr.awt.demo.domain.book;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long id;
    private String title;
    private String author;
    private String imageURL;
    private String blurb;
    private int available;
    private int total;

    public boolean removeFromStack(){
        if(available==0){
            return false;
        }
        available--;
        return true;
    }

}
