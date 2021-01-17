package pl.sda.blogservicedata.model;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //automat generuj id do tabeli
    private long id;    //lub UUID=uniq univer identifier - losowy numer
    private String author;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Topic topic;    //mogłaby to być osobna tabela, a tak zaciaga z enuma
    private LocalDateTime created;
    private LocalDateTime modified;

    public BlogPost(long id, String author, String title, String content, Topic topic, LocalDateTime created) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.created = created;
    }
}
