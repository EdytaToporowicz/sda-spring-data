package pl.sda.blogservicedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import pl.sda.blogservicedata.exception.BlogPostNotFoundException;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.mapping.BlogPostMapper;
import pl.sda.blogservicedata.model.request.BlogPostDto;
import pl.sda.blogservicedata.repository.BlogPostRepository;

import java.time.LocalDateTime;
import java.util.List;


@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;

    @Autowired
    public BlogPostService(final BlogPostRepository blogPostRepository, final BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
    }

    public BlogPost save(final BlogPostDto blogPostDto) {
        BlogPost blogPost = blogPostMapper.map(blogPostDto);
        blogPost.setCreated(LocalDateTime.now());
        return blogPostRepository.save(blogPost);
    }

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public BlogPost findById(final long id) {
        return blogPostRepository.findById(id).orElseThrow(() -> new BlogPostNotFoundException("blogpost not found"));
    }

    public List<BlogPost> findByCriteria(final Topic topic, String author, String titlePhrase) {        //sprawdz
        ExampleMatcher matcher = ExampleMatcher.matchingAll()                           //temat dodatkowy - query by example
                .withMatcher("topic", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("author", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains()) //w tytule się zawiera
                .withIgnorePaths("id", "content", "created", "modified");
        Example<BlogPost> example = Example.of(new BlogPost(0l, author, titlePhrase, null, topic, null), matcher);

        return blogPostRepository.findAll(example);


//        if (topic != null && author != null && titlePhrase != null) {
//            return blogPostRepository.findByAllCriteria(topic, author, titlePhrase);
//        }
//        if (topic != null && author != null) {
//            return blogPostRepository.findAllByTopicAndAuthor(topic, author);
//        }
//        if (topic != null && titlePhrase != null) {
//            return blogPostRepository.findAllByTopicAndTitleContaining(topic, titlePhrase);
//        }
//        if (titlePhrase != null && author != null) {
//            return blogPostRepository.findAllByTitleContainingAndAuthor(titlePhrase, author);
//        }
//        if (titlePhrase != null) {
//            return blogPostRepository.findAllByTitleContaining(titlePhrase);
//        }
//        if (author != null) {
//            return blogPostRepository.findAllByAuthor(author);
//        }
//        if (topic != null) {
//            return blogPostRepository.findAllByTopic(topic);
//        }
//        return blogPostRepository.findAllByTopic(topic);
    }

    public void removeById(final long id) {
        blogPostRepository.deleteById(id);
    }

    public BlogPost updateBlogPost(BlogPostDto blogPostDto, long blogPostId) {
        BlogPost blogPost = blogPostRepository.findById(blogPostId).orElseThrow(() -> new BlogPostNotFoundException("blog post not found by id: " + blogPostId));

        blogPost.setAuthor(blogPostDto.getAuthor());
        blogPost.setTitle(blogPostDto.getTitle());
        blogPost.setContent(blogPostDto.getContent());
        blogPost.setTopic(blogPostDto.getTopic());
        blogPost.setModified(LocalDateTime.now());

        return blogPostRepository.save(blogPost);
    }
}
