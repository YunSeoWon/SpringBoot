package com.board.boardweb;

import com.board.boardweb.domain.Board;
import com.board.boardweb.domain.enums.BoardType;
import com.board.boardweb.domain.User;
import com.board.boardweb.repository.BoardRepository;
import com.board.boardweb.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

// JUnit에 내장된 러너를 사용하지 않고, 어노테이션에 정의된 클래스를 호출한다.
@RunWith(SpringRunner.class)
// JPA test를 위한 전용 어노테이션. 첫 설계 시 엔티티 간의 관계 설정 및 기능 테스트를 가능하게 해준다.
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init() {
        User user = userRepository.save(User.builder()
        .name("havi")
        .password("test")
        .email(email)
        .createdDate(LocalDateTime.now())
        .build());

        boardRepository.save(Board.builder()
        .title(boardTestTitle)
        .subTitle("서브 타이틀")
        .content("콘텐츠")
        .boardType(BoardType.free)
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .user(user)
        .build());
    }

    @Test
    public void test() {
        User user = userRepository.findByEmail(email);
        assertThat(user.getName(), is("havi"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is(email));

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubTitle(), is("서브 타이틀"));
        assertThat(board.getContent(), is("콘텐츠"));
        assertThat(board.getBoardType(), is(BoardType.free));
    }


}
