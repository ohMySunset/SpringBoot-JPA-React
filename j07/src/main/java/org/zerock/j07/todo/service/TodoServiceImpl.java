package org.zerock.j07.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.TodoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor // TodoRepository 알아서 주입해줌
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    public Long register(TodoDTO dto) {

        log.info(dto);

        // dto -> Entity 변환
        Todo entity = dtoToEntity(dto);

        // 변환된 entiry객체 저장
        todoRepository.save(entity);

        return entity.getTno();
    }

    @Override
    public TodoDTO read(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        log.info(result);

        if(result.isPresent()){
            Todo todo = result.get();
            return entityToDTO(todo);
        }
        return null;
    }

    @Override
    public Long remove(Long tno) {

        todoRepository.delete(Todo.builder().tno(tno).build());

        return tno;
    }

    @Override
    public TodoDTO modify(TodoDTO dto) {

        Optional<Todo> result = todoRepository.findById(dto.getTno());

        if(result.isPresent()){

            Todo entity = result.get();
            entity.changeTitle(dto.getContent());
            entity.changeDel(dto.isDel());

            todoRepository.save(entity);

            return entityToDTO(entity);
        }

        return null;
    }


}
