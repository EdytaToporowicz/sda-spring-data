package pl.sda.blogservicedata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.blogservicedata.exception.BlogPostNotFoundException;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.mapping.BlogPostMapper;
import pl.sda.blogservicedata.model.request.BlogPostDto;
import pl.sda.blogservicedata.repository.BlogPostRepository;

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
        return blogPostRepository.save(blogPost);
    }

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public BlogPost findById(final long id) {
        return blogPostRepository.findById(id).orElseThrow(() -> new BlogPostNotFoundException("blogpost not found"));
    }

    public List<BlogPost> findByTopic(final Topic topic) {
        return blogPostRepository.findAllByTopic(topic);
    }

    public void removeById(final long id) {
        blogPostRepository.deleteById(id);
    }

}
