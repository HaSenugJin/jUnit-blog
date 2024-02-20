package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class) // 내가 만든 클래스는 import 해줘야 한다.
@DataJpaTest // DB 관련 객체들이 IoC에 뜬다.
public class BoardRepositoryTest {

    @Autowired // Test에서 DI 하는 코드
    private BoardRepository boardRepository;

    @Test void deleteById_test() {
        // given
        int id = 1;
        Board board1 = boardRepository.selectOne(id);
        System.out.println("board : " + board1); // 요소 1번의 내용

        // when
        boardRepository.deleteById(id);

        // then
        Board board2 = boardRepository.selectOne(id);
        System.out.println("board : " + board2); // null
    }


    @Test void update_test() {
        // given
        String title = "제목update";
        String content = "내용update";
        String author = "update";
        int id = 5;
        // int id = 50; // board에 null나옴

        // when
        boardRepository.update(title, content, author, id);

        // then
        Board board = boardRepository.selectOne(id);
        System.out.println("board : " + board);
    }

    @Test
    public void selectAll_test() {
        // given
        // selectAll 메서드에 인수를 넘겨주지 않았으니 given 이 없다.

        // when
        List<Board> boardList = boardRepository.selectAll();

        // then
        Assertions.assertThat(boardList.get(0).getTitle()).isEqualTo("제목1");
        Assertions.assertThat(boardList.get(0).getContent()).isEqualTo("내용1");
        Assertions.assertThat(boardList.get(0).getAuthor()).isEqualTo("홍길동");
    }

    @Test
    public void selectOne_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.selectOne(id);

        // then (상태 검사)
        // System.out.println("찾은 값 : " + board);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.getAuthor()).isEqualTo("홍길동");
    }

    @Test
    public void insert_test(){ // Test 메서드는 파라미터, 리턴이 없다.
        // given
        String title = "제목10";
        String content = "내용10";
        String author = "이순신";

        // when
        boardRepository.insert(title, content, author);

        // then -> 눈으로 확인 (쿼리)
    } // Rollback (자동)
}