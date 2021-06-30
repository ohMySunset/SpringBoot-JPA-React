package org.zerock.j07.board;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.repository.BoardRepository;

import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("dev") // 개발환경과 배포환경을 구분지어줄 수 있음.
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertDummits(){

        IntStream.rangeClosed(1,100).forEach(i->{

            Board board = Board.builder()
                    .title("제목...")
                    .content("내용...")
                    .writer("user00")
                    .build();

            boardRepository.save(board);

            log.info(board);
        });//end loop
    }
}
