package website.introduceweb.domain.board.entity;

import lombok.*;
import website.introduceweb.domain.board.post.entity.Post;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "board")
@AllArgsConstructor
@Getter
@Builder
public class Board implements Serializable {
    @Id
    @GeneratedValue
    private Long board_number;
    
    @Column(name = "board_title", length = 60, nullable = false)
    private String board_title;
    //board_title 값이 자동으로 안 넘어 오는 문제를 해결해야함

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private Set<Post> posts;

    public Board() {

    }

    public Board(String board_title) {
        this.board_title = board_title;
    }
}