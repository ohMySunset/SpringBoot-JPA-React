package org.zerock.j07.todo.service;

import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.todo.entity.Todo;

public interface TodoService {

    // 데이터 저장
    Long register(TodoDTO dto);

    // dto -> Entity 변환
    default Todo dtoToEntity(TodoDTO dto){
        return Todo.builder().tno(dto.getTno())
                            .content(dto.getContent())
                            .del(dto.isDel())
                            .build();
    }

    // 데이터 읽기
    TodoDTO read(Long tno);

    default TodoDTO entityToDTO(Todo entity){
        return TodoDTO.builder().tno(entity.getTno())
                .content(entity.getContent())
                .del(entity.isDel())
                .redData(entity.getRegDate())
                .modData(entity.getModDate())
                .build();
    }

    Long remove(Long tno);

    TodoDTO modify(TodoDTO dto);
}
