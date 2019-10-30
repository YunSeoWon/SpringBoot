package com.board.boardweb.security;

import com.board.boardweb.domain.Member;
import com.board.boardweb.exception.EmailNotFoundException;
import com.board.boardweb.exception.PasswordNotEqualException;
import com.board.boardweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

// Autowired로 주입할 때 scan이 될 수 있게끔 해준다.
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    MemberService memberService;

    /*
        Spring Security가 인증을 수행하기 위해서 호출을 해주는 메서드이다.
        이 메서드로 넘겨지는 authentication 인자를 이용해서 사용자의 입력값을 얻고 인증을 수행한다.
        인증이 성공되는 경우, Authentication 인터페이스를 구현한 객체를 반환한다.
        인증이 실패되는 경우, 예외를 발생시킨다.
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        System.out.println("Authentication");
        // 로그인 페이지에서 usernameParameter 전송한 값을 얻어온다.
        // 로그인 페이지에서 passwordParameter로 전송한 값을 얻어온다.
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        Member member = memberService.getMember(email, password);

        // password와 같은 민감한 정보는 삭제. (로직이니까 변경해도 됨..)
        member.setPassword(null);

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + member.getRoles()));
        return new UsernamePasswordAuthenticationToken(member, null, authorities);

    }

    @Override
    public boolean supports(Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}