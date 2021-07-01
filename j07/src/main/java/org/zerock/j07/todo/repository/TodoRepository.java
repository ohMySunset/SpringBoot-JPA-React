package org.zerock.j07.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.dynamic.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

    @Modifying
    @Query("update Todo set content =:content where tno =:tno")
    void updateContent(String content, Long tno);

    @Query("select t from Todo t where t.content like concat('%', :keyword, '%') order by t.tno desc") // @query 어노테이션에서는 *를 쓸 수 없고 alias를 사용한다.
    Page<Todo> getList(String keyword, Pageable pageable); // Pageable -> limit, order by 역할을 해줌.

}
