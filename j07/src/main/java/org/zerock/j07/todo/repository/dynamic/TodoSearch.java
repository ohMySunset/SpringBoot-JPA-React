package org.zerock.j07.todo.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.j07.todo.entity.Todo;

public interface TodoSearch {

    Todo doA();

    // 페이징 처리를 위한 메서드
    Page<Todo> listWithSearch(String keyword, Pageable pageable);
}
