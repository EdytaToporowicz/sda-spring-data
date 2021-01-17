package pl.sda.blogservicedata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, Long> { //Long - a nie prosty!

    // private EntityManager entityManager;               //żeby podpiąć BD

    //    public BlogPostRepository(EntityManager entityManager) {
    //        this.entityManager = entityManager;
    //    }


    //usuwamy metody które mamy w repo JPA

//    @Transactional      //operacje delete insert i update
//    public BlogPost save(final BlogPost blogPost) {
//        blogPost.setCreated(LocalDateTime.now());
//        entityManager.persist(blogPost);
//        return blogPost;
//    }


//    public List<BlogPost> findAll() {
//        return entityManager.createQuery("select b from blog_posts b", BlogPost.class).getResultList();
//
//    }

//    @Transactional
//    public void remove(long blogPostId) {
//        entityManager.createQuery("delete from blog_posts b where b.id = :id")
//                .setParameter("id", blogPostId)
//                .executeUpdate();
//    }

    Optional<BlogPost> findById(final long blogPostId);

    //    public BlogPost findById(final long blogPostId) {
//        return entityManager.createQuery("select b from blog_posts b where b.id = :id", BlogPost.class)
//                .setParameter("id", blogPostId)
//                .getSingleResult();
//    }


    List<BlogPost> findAllByTopic(Topic topic);

//    public List<BlogPost> findByTopic(Topic topic) {
//        return entityManager.createQuery("select b from blog_posts b where b.topic=:topic", BlogPost.class)
//                .setParameter("topic", topic)
//                .getResultList();
//
//    }
List<BlogPost> findAll();
}
