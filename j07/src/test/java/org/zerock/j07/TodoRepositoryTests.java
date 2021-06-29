package org.zerock.j07;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.TodoRepository;
import java.util.Optional;
import java.util.stream.IntStream;

@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;
    @Test
    public void test1(){
        log.info(todoRepository.getClass().getName());

    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,300).forEach(i -> {
            Todo todo = Todo.builder().content("내용...." + i).build();
            todoRepository.save(todo);

        }); // loop
    }

    @Test
    public void testSelect(){
        // Select 처리
        // Optional<T> : T타입의 객체를 포장해주는 래퍼클래스
        Optional<Todo> result = todoRepository.findById(1L);

        //log.info(result.get());

        result.ifPresent(todo -> log.info(todo));
    }

    @Test
    public void testPaging(){
        // 페이징 처리
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        // finaAll() - Pageable타입
        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(todo -> log.info("페이징 : "+todo));

    }

    @Test
    public void testUpdate(){
        // Update 처리
        Optional<Todo> result = todoRepository.findById(300L);

        // 만약에 결과가 존재한다면
        result.ifPresent(todo -> {
            todo.changeTitle("300번 내용 수정");
            todoRepository.save(todo);
        });
    }

    @Test
    public void testDelete(){
        // Delete 처리
        todoRepository.delete(Todo.builder().tno(300L).build());

    }


}
