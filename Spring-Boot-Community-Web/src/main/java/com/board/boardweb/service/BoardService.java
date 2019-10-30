package com.board.boardweb.service;

import com.board.boardweb.domain.Board;
import com.board.boardweb.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



/*
  Board와 관련된 비즈니스 로직을 구현함.
  구현 부분
    - Board의 리스트를 page 단위로 반환
    - Board 갹체를 id를 이용하여 찾아 반환
 */

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> findBoardList(Pageable pageable) {
        // k번째 페이지의 데이터 가져오는 역할.
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ?
                0 : pageable.getPageNumber() - 1, pageable.getPageSize());

        return boardRepository.findAll(pageable);
    }

    public Board findBoardByIdx(Long idx) {
        return boardRepository.findById(idx).orElse(new Board());
    }
}
