package pl.sda.blogservicedata.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.request.BlogPostDto;
import pl.sda.blogservicedata.service.BlogPostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping(path = "/blogPosts", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<BlogPost>> findByTopic(final @RequestParam(required = false) Topic topic,
                                                      @RequestParam(required = false) String author,
                                                      @RequestParam(required = false) String titlePhrase) {
        if (topic == null && author == null && titlePhrase == null) {
            return ResponseEntity.ok(blogPostService.findAll());

        }
        return ResponseEntity.ok(blogPostService.findByCriteria(topic, author, titlePhrase));
    }

    @GetMapping(path = "/blogPosts/{blogPostId}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BlogPost> findById(final @PathVariable long blogPostId) {
        return ResponseEntity.ok(blogPostService.findById(blogPostId));
    }

    @DeleteMapping("/blogPosts/{blogPostId}")
    public ResponseEntity deleteById(final @PathVariable long blogPostId) {
        blogPostService.removeById(blogPostId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/blogPosts", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BlogPost> create(final @RequestBody @Valid BlogPostDto blogPostDto) {
        return ResponseEntity.ok(blogPostService.save(blogPostDto));
    }

    @PutMapping(path = "/blogPosts/{blogPostId}")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @RequestBody BlogPostDto blogPostDto, @PathVariable long blogPostId) {
        return ResponseEntity.ok(blogPostService.updateBlogPost(blogPostDto, blogPostId));
    }
}
