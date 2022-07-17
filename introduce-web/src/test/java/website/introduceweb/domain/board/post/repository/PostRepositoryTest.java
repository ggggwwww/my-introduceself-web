package website.introduceweb.domain.board.post.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import website.introduceweb.domain.board.dto.BoardDto;
import website.introduceweb.domain.board.entity.Board;
import website.introduceweb.domain.board.post.dto.PostDTO;
import website.introduceweb.domain.board.post.entity.Post;
import website.introduceweb.domain.board.repository.BoardRepositoryImpl;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryTest extends Assertions {

    @Autowired
    BoardRepositoryImpl boardRepository;
    @Autowired
    PostRepositoryImpl postRepository;

    @Test
    public void 게시글_등록() throws Exception{
        //Given
        BoardDto boardDto = new BoardDto();
        Board board = boardDto.toEntity("예시 게시판");
        boardRepository.save(board);

        PostDTO postDTO = new PostDTO();
        Post post = postDTO.toEntity("게시글1", "재미있는 내용", board);
        //When
        postRepository.save(post);
        //Then
        assertThat(post.getPost_title()).isEqualTo("게시글1");
        assertThat(post.getPost_contents()).isEqualTo("재미있는 내용");
        assertThat(post.getBoard().getBoard_title()).isEqualTo("예시 게시판");
    }
}