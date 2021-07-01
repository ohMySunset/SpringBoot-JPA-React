package org.zerock.j07.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    // 게시글 기준으로 댓글을 페이징 처리하기 위한 메서드
    Page<Reply> getByBoard(Board board, Pageable pageable);



}
