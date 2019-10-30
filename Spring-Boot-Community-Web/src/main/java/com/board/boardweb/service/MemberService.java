package com.board.boardweb.service;


import com.board.boardweb.domain.Member;
import com.board.boardweb.domain.MemberRole;
import com.board.boardweb.domain.enums.RoleType;
import com.board.boardweb.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void signup(Member member) {
        MemberRole role = new MemberRole();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        role.setRoleType(RoleType.user);
        member.addRole(role);
        memberRepository.save(member);
    }
}
