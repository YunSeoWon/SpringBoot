package com.board.boardweb.service;


import com.board.boardweb.domain.Member;
import com.board.boardweb.domain.MemberRole;
import com.board.boardweb.domain.enums.RoleType;
import com.board.boardweb.exception.EmailNotFoundException;
import com.board.boardweb.exception.PasswordNotEqualException;
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
        memberRepository.save(member);
    }


    public Member getMember(String email, String password) throws EmailNotFoundException, PasswordNotEqualException {
        Member member = memberRepository.findByEmail(email);
        if(member == null) throw new EmailNotFoundException("등록되지 않은 이메일입니다.");
        else if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new PasswordNotEqualException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }
}
