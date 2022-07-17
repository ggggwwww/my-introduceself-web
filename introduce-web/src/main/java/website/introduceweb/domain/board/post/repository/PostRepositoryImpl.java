package website.introduceweb.domain.board.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import website.introduceweb.domain.board.post.entity.Post;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{
    private final EntityManager em;

    @Transactional
    @Override
    public void save(Post post) {
        em.persist(post);
    }

    @Override
    public List<Post> findAll() {
        String jpql = "select p from Post p";
        return em.createQuery(jpql, Post.class)
                .getResultList();
    }

    @Override
    public List<Post> findByNumber(Long number) {
        String jpql = "select p from Post p where p.post_number = :number ";
        return em.createQuery(jpql, Post.class)
                .setParameter("number", number)
                .getResultList();
    }

    @Override
    public List<Post> findByName(String title) {
        String jpql = "select p from Post p where p.board.board_title= :title ";
        return em.createQuery(jpql, Post.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Post> findByTitleAndNumber(String title, Long number) {
        String jpql = "select p from Post p where p.post_number = :number and p.board.board_title = :title ";
        return em.createQuery(jpql, Post.class)
                .setParameter("number", number)
                .setParameter("title", title)
                .getResultList();
    }


}
