package website.introduceweb.domain.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import website.introduceweb.domain.board.entity.Board;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final EntityManager em;

    @Override
    public void save(Board board) {
        em.persist(board);
    }

    @Override
    public List<Board> findAll() {
        String jpql = "select b from Board b";
        return em.createQuery(jpql, Board.class)
                .getResultList();
    }

    @Override
    public List<Board> findByNumber(Long number) {
        String jpql = "select b from Board b where b.board_number = :number";
        return em.createQuery(jpql, Board.class)
                .setParameter("number", number)
                .getResultList();
    }

    @Override
    public List<Board> findByName(String name) {
        String jpql = "select b from Board b where b.board_title = :name";
        return em.createQuery(jpql, Board.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public Board findByTitle(String title){
        String jpql = "select b from Board b where b.board_title = :title";
        return em.createQuery(jpql, Board.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public void updateBoard(String board_title, String new_board_title){

        String jpql = "update Board b set b.board_title = :new_board_title where b.board_title = :board_title";

       em.createQuery(jpql)
                .setParameter("new_board_title", new_board_title)
                .setParameter("board_title", board_title)
                .executeUpdate();
    }


    @Override
    public void deleteBoard(String board_title) {
        String jpql = "delete from Board b where b.board_title = :board_title";
        em.createQuery(jpql)
                .setParameter("board_title", board_title)
                .executeUpdate();

    }

}
