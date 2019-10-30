package com.board.boardweb.controller;


import com.board.boardweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class BoardController {
    // Service: Controller와 Repository를 연결해주는 객체
    @Autowired
    private BoardService boardService;

    @GetMapping("board")
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        return "board/form";
    }

    @GetMapping("board/form")
    public String board() {
        return "board/form";
    }


    @GetMapping({"", "board/list"})
    public String list(@PageableDefault Pageable pageable, Model model) {
        // thymleaf에게 데이터를 넘겨줌.
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "board/list";
    }

}
