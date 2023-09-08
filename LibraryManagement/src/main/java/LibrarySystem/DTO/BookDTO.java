package LibrarySystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private String title;
    private String author;
    private Long userId;
}
