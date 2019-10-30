package com.board.boardweb.service;

import com.board.boardweb.domain.SecurityMember;
import com.board.boardweb.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


/*
    UserDetailsService를 이용해 로그인 인증 처리를 해야 한다.
    로그인 인증 처리를 구현한 후, 이를 HttpSecurity 객체가 사용하도록 지정하면
    우리가 만든 인증 로직을 바탕으로 동작하게 된다.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService  {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, NoSuchElementException {
        System.out.println(email);
        return Optional.ofNullable(memberRepository.findByEmail(email))
                .filter(m -> m != null)
                .map(m -> new SecurityMember(m)).get();
    }
}
