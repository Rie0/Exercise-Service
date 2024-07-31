package org.twspring.exerciseservice.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotNull(message = "IDs cannot be empty")
    @Positive(message = "Enter an ID, should be a positive number")
    private int id;

    @NotEmpty(message = "Title cannot be empty")
    @Length(max = 100, message = "Title is too long, maximum number of character are 100")
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    @Length(min = 5, max = 20, message = "Author name must have between 5 to 20 characters")
    private String author;

    @NotEmpty(message = "Content cannot be empty")
    @Length(min = 200, message = "Content must have at least 200 characters")
    private String content;

    @NotEmpty(message = "Category cannot be empty")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "Only allowed categories are: politics, sports, technology")
    private String category;

    @NotEmpty(message = "Image URL cannot be empty")
    @URL (message = "Invalid URL")//added, checks if http: or https: exists in the string
    private String imageUrl;

    @AssertFalse(message = "Is published must be initially set to false")
    private boolean isPublished;

    @JsonFormat(pattern = "yyyy-MM-dd") //check the format
    private LocalDate publishedDate;
}
