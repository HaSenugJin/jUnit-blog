package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    public void update(String title, String content, String author, int id) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ?, author = ? where id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, author);
        query.setParameter(4, id);
        query.executeUpdate();
    }

    public Board selectOne(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        query.setParameter(1, id);

        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Board> selectAll() {
        Query query = em.createNativeQuery("select * from board_tb", Board.class);

        List<Board> boardList = query.getResultList();

        return boardList;
    }

    @Transactional
    public void insert(String title, String content, String author){
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values(?, ?, ?)");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, author);

        query.executeUpdate();
    }
}