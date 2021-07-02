package org.zerock.j07.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.PageMaker;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Override
    public ListResponseDTO<TodoDTO> list(ListRequestDTO dto) {

        Pageable pageable = dto.getPageable();
        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(), pageable);

        // Function<T,R> : 객체 T를 R로 매핑
        Function<Todo, TodoDTO> fn = (todo)-> entityToDTO(todo);

        List<TodoDTO> dtoList = result.getContent().stream()
                                                    .map(fn)
                                                    .collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(dto.getPage(),dto.getSize(),(int)result.getTotalElements());

        log.info(pageMaker);

        ListResponseDTO<TodoDTO> listResult = ListResponseDTO.<TodoDTO>builder()
                                                                .dtoList(dtoList)
                                                                .pageMaker(pageMaker)
                                                                .listRequestDTO(dto).build();

        log.info(listResult);

        return listResult;
    }


}
