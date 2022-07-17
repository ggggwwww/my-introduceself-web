package website.introduceweb.domain.board.repository;

import website.introduceweb.domain.board.entity.Board;

import java.util.List;

public interface BoardRepository{

    void save(Board board);

    List<Board> findAll();

    List<Board> findByNumber(Long number);

    List<Board> findByName(String name);

    Board findByTitle(String title);

    void deleteBoard(String board_title);

    void updateBoard(String board_title, String new_board_title);
}
