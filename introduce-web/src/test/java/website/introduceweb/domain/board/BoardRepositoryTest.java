package website.introduceweb.domain.board;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import website.introduceweb.domain.board.dto.BoardDto;
import website.introduceweb.domain.board.entity.Board;
import website.introduceweb.domain.board.repository.BoardRepositoryImpl;
import website.introduceweb.domain.board.service.BoardService;

import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class BoardRepositoryTest{

    @Autowired
    BoardRepositoryImpl repository;
    @Autowired
    BoardService service;

    @Test
    public void 게시판_등록() throws Exception{
        //Given
        BoardDto boardDto = new BoardDto();
        Board board = boardDto.toEntity("sans");
        //When
        repository.save(board);
        //Then
        assertThat(board.getBoard_title()).isEqualTo("sans");
    }

    @Test
    public void 게시판_삭제() throws Exception{
        //Given
        BoardDto dto = new BoardDto();
        Board board = dto.toEntity("테스트 게시판");
        service.join(board);
        //When
        repository.deleteBoard("테스트 게시판");
        //Then
    }

    @Test
    public void 중복게시판_검열() throws Exception{
        //Given
        BoardDto dto = new BoardDto();
        Board board1 = dto.toEntity("board1");
        Board board2 = dto.toEntity("board1");
        //When
        repository.save(board1);
        try{
            repository.save(board2);
        }   //Then
        catch(IllegalStateException e){
            assertThat(board1.getBoard_title()).isEqualTo(board2.getBoard_title());
        }

    }

    @Test
    public void 게시판_업데이트() throws Exception{
        //Given
        BoardDto dto = new BoardDto();
        Board board = dto.toEntity("게시판 ");
        repository.save(board);
        //When
        repository.updateBoard("게시판", "새 게시판");
        //Then
        System.out.println("board.getBoard_title() = " + board.getBoard_title());
        //assertThat(board.getBoard_title()).isEqualTo("Sans");
    }
}