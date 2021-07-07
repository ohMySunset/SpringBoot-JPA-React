package org.zerock.j07.todo.repository.dynamic;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j07.todo.entity.QTodo;
import org.zerock.j07.todo.entity.Todo;

import java.util.List;

// 인터페이스를 상속하는 클래스의 이름뒤에 Impl 꼭 붙여주어야함.
@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {


    public TodoSearchImpl() {
        super(Todo.class);  // 부모클래스에 있는 생성자 호출
    }

    @Override
    public Todo doA() {

        log.warn("doA..............");
        log.warn("doA..............");
        log.warn("doA..............");

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        // where content like '%9%' and tno=170

        //where todo.tno = 170
        query.where(todo.tno.gt(170L));

        //content like '%9%'
        //query.where(todo.content.like("%9%"));

        // 페이징 처리
        query.orderBy(todo.tno.desc());
        query.offset(0);
        query.limit(10);

        List<Todo> result = query.fetch();

        long count = query.fetchCount();

        log.warn("===============");
        log.info("Count :" + count);
        log.warn(result);
        return null;
    }

    @Override
    public Page<Todo> listWithSearch(String keyword, Pageable pageable) {

        log.warn("listWithSearch........");

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        if (keyword != null && keyword.trim().length() != 0) {
            query.where(todo.content.contains(keyword));
        }

        query.where(todo.tno.gt(0L)); // 인덱스를 사용하도록 유도해주는 코드!
        query.orderBy(todo.tno.desc());

        // 앞에서 파라미터로 던져준 Pageable = 0,10
        // paging
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Todo> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

}
