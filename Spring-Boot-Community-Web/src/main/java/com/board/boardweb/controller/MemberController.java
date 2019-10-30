package com.board.boardweb.controller;

import com.board.boardweb.domain.LoginForm;
import com.board.boardweb.domain.Member;
import com.board.boardweb.exception.EmailNotFoundException;
import com.board.boardweb.exception.PasswordNotEqualException;
import com.board.boardweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("join")
    public String join() {
        return "member/join";
    }

    @PostMapping("create")
    public String create(@RequestParam("uname") String name, @RequestParam("uemail") String email,
                         @RequestParam("upw") String password, @RequestParam("upw_check") String pwCheck) {

        Member member = Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();

        memberService.signup(member);
        return "redirect:/board/list";
    }

//    @GetMapping("login")
//    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
//        return "member/login";
//    }
//

//    @PostMapping("authenticate")
//    public String authenticate(@RequestParam("email") String email, @RequestParam("password") String password) {
//        try {
//            Member member = memberService.getMember(email, password);
//            System.out.println(member.getName() + ", " + member.getEmail());
//        } catch (EmailNotFoundException ee) {
//            System.out.println(ee.getMessage());
//            return "redirect:/member/login";
//        } catch (PasswordNotEqualException pe) {
//            System.out.println(pe.getMessage());
//            return "redirect:/member/login";
//        }
//
//
//        return "redirect:/";
//    }

    @GetMapping("/login")
    public String loginForm(HttpServletRequest req) {
        String referer = req.getHeader("Referer");
        req.getSession().setAttribute("prevPage", referer);
        return "member/login";
    }



}
