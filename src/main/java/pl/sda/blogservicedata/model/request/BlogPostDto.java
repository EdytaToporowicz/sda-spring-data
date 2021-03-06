package pl.sda.blogservicedata.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.blogservicedata.model.Topic;

import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {


    private String author;
    @NotNull
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^\\p{javaUpperCase}.*", message = "Tytuł musi zaczynać się wielką literą")
    private String title;
    @NotNull
    @Size(max = 1000)
    private String content;
    @NotNull
    private Topic topic;

//    @AssertTrue(message = "Tytuł musi zaczynać się wielką literą")
//    public boolean isTitleValid() {
//        return Character.isUpperCase(title.charAt(0));
//    }

}
