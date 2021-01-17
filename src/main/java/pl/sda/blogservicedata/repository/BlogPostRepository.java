package pl.sda.blogservicedata.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BlogPostRepository {

    private EntityManager entityManager;               //żeby podpiąć BD

    public BlogPostRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional      //operacje delete insert i update
    public BlogPost save(final BlogPost blogPost) {
        blogPost.setCreated(LocalDateTime.now());
        entityManager.persist(blogPost);
        return blogPost;
    }


    public List<BlogPost> findAll() {
        return entityManager.createQuery("select b from blog_posts b", BlogPost.class).getResultList();

    }

    @Transactional
    public void remove(long blogPostId) {
        entityManager.createQuery("delete from blog_posts b where b.id = :id")
                .setParameter("id", blogPostId)
                .executeUpdate();
    }


    public BlogPost findById(final long blogPostId) {
        return entityManager.createQuery("select b from blog_posts b where b.id = :id", BlogPost.class)
                .setParameter("id", blogPostId)
                .getSingleResult();
    }

    public List<BlogPost> findByTopic(Topic topic) {
        return entityManager.createQuery("select b from blog_posts b where b.topic=:topic", BlogPost.class)
                .setParameter("topic", topic)
                .getResultList();

    }
}
