package website.introduceweb.domain.board.post.repository;

import website.introduceweb.domain.board.post.entity.Post;

import java.util.List;

public interface PostRepository {
    void save(Post post);

    List<Post> findAll();

    List<Post> findByNumber(Long number);

    List<Post> findByName(String title);

    List<Post> findByTitleAndNumber(String title, Long number);
}
