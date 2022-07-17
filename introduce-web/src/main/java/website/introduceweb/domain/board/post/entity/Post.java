package website.introduceweb.domain.board.post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import website.introduceweb.domain.board.entity.Board;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long post_number;

    @Column
    private String post_title;

    @Column
    private String post_contents;

    @ManyToOne(cascade = CascadeType.ALL)
    private Board board;

    public Post() {

    }
}
