package com.board.boardweb;

import com.board.boardweb.domain.Board;
import com.board.boardweb.domain.Member;
import com.board.boardweb.domain.MemberRole;
import com.board.boardweb.domain.enums.BoardType;
import com.board.boardweb.domain.enums.RoleType;
import com.board.boardweb.repository.BoardRepository;
import com.board.boardweb.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class BoardWebApplication {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        RoleType roleType = RoleType.admin;
        System.out.println(roleType);
        System.out.println(roleType.getValue());
        SpringApplication.run(BoardWebApplication.class, args);
    }

    /*
    CommandLineRunner: 애플리케이션 구동 후 특정 코드를 실행시키고 싶을 때 직접 구현하는 인터페이스
    애플리케이션 구동 시 테스트 데이터를 함께 생성하여 데모 프로젝트를 실행/테스트할 때 편리함.
     */

    @Bean
    public CommandLineRunner runer(MemberRepository memberRepository,
                                   BoardRepository boardRepository) throws Exception {
        return (args) -> {
            Member m = Member.builder()
                    .name("aa")
                    .password(passwordEncoder.encode("test"))
                    .email("aa@aa")
                    .createdDate(LocalDateTime.now())
                    .build();
            MemberRole r = new MemberRole();
            r.setRoleType(RoleType.user);
            m.addRole(r);
            Member member = memberRepository.save(m);




            IntStream.rangeClosed(1, 200).forEach(index ->
                    boardRepository.save(Board.builder()
                    .title("게시글"+index)
                    .subTitle("순서"+index)
                    .content("콘텐츠")
                    .boardType(BoardType.free)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .member(member)
                    .build()));

        };
    }

}
