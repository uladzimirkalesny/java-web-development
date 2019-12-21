package by.epam.training.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private int id;
    private String title;
    private String description;
    private Set<Author> authors;
    private int unitsInStock;

    @Builder(builderMethodName = "createBookBuilder")
    public Book(int id, String title, Set<Author> authors, String description, int unitsInStock) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.unitsInStock = unitsInStock;
    }
}
