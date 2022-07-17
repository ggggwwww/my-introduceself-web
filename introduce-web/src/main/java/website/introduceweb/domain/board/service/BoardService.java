package website.introduceweb.domain.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import website.introduceweb.domain.board.entity.Board;
import website.introduceweb.domain.board.repository.BoardRepositoryImpl;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepositoryImpl repository;

    public void validDuplicateName(String name){
        List<Board> boardList= repository.findByName(name);
        if(!boardList.isEmpty()){
            throw new IllegalStateException("중복된 이름의 게시판입니다!");
        }
    }

    @Transactional
    public void join(Board board){

        validDuplicateName(board.getBoard_title());
        repository.save(board);
        log.info("\n게시판 등록 성공!\n게시판 번호: {} & 게시판 제목: {}",
                board.getBoard_number(), board.getBoard_title());
    }

    public List<Board> showAll(){

        List<Board> boardList = repository.findAll();
        log.info("\n전체 게시판 조회");
        for (int i = 0; i < boardList.size(); i++) {
            log.info("\n게시판 번호: {} & 게시판 제목: {}",
                    boardList.get(i).getBoard_number(), boardList.get(i).getBoard_title());
        }
        return boardList;
    }

    @Transactional
    @Modifying(clearAutomatically = true)
    public void editBoardTitle(String board_title, String new_board_title){
        repository.updateBoard(board_title, new_board_title);
        log.info("'{}' 게시판의 제목이 => '{}' 게시판으로 수정되었습니다!", board_title, new_board_title);
    }

    @Transactional
    @Modifying(clearAutomatically = true)
    public void deleteBoard(String board_title){
        repository.deleteBoard(board_title);
        log.info("{} 게시판이 삭제되었습니다!", board_title);
    }
}
