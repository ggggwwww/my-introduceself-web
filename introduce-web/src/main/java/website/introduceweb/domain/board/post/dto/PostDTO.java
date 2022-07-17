package website.introduceweb.domain.board.post.dto;

import lombok.Getter;
import lombok.Setter;
import website.introduceweb.domain.board.entity.Board;
import website.introduceweb.domain.board.post.entity.Post;

@Getter @Setter
public class PostDTO {
    private String post_title;
    private String post_contents;
    private Board board;
    public Post toEntity(String post_title, String contents, Board board){
        return Post.builder()
                .post_title(post_title)
                .post_contents(contents)
                .board(board)
                .build();
    }
}
