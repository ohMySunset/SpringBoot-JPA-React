package org.zerock.j07.reply;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.entity.Reply;
import org.zerock.j07.board.repository.ReplyRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert() {
        // 게시글에 댓글을 달기 위해 게시글 객체 가져오기
        IntStream.rangeClosed(1, 1000).forEach(i -> {

            long bno = (int) (Math.random() * 200) + 1;
            Board board = Board.builder().dno(bno).build();

            Reply reply = Reply.builder().replyText("Hi Hello..").board(board).build();

            replyRepository.save(reply);
        });
    }

    @Test
    public void testRead() {
        // 1번 댓글
        Optional<Reply> result = replyRepository.findById(1L);
        // join문이 자동으로 생성되어 board까지 불러옴.  --> eager로딩(DEFAULT)  <-> 필요한 순간까지 미루다가 필요한 데이터만 가져오는 것 : Lazy
        log.info(result);

        result.ifPresent(reply -> log.info(reply));

        // Fetch방식
        // Eager <-> Lazy
        // ManyToOne -> Eager -> N+1문제 발생

    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
        Page<Reply> result = replyRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(reply -> {

            //log.info(reply.getRno());
            //log.info(reply.getReplyText());
            log.info("---------------------");

            log.info(reply);
            // 만약 Reply에서 참조하는 Board에 Lazy로딩을 걸고 Reply 속성에 log를 찍어 출력하기위해 .toString을 적용할 경우 필요없는 board까지 적용됨.
            // 이 경우 Reply에서 @ToString(exclude = {"board"})를 해주어야 No Session에러가 나지 않음.
        });
    }

    @Test
    public void testByBoard() {
        // 게시글에서 dno를 기준으로 조회해서 페이징 처리 ---> 댓글 페이징의 경우 index 사용을 고려해야한다.
        Board board = Board.builder().dno(187L).build();
        Pageable pageable = PageRequest.of(0, 10);

        replyRepository.getByBoard(board, pageable);
    }

}
