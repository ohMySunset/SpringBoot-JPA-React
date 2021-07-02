package org.zerock.j07.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.repository.dynamic.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    // join문을 쓸 때는 Page의 오브젝트 배열을 반환 타입으로 지정해준다.
    // 반환타입이 배열일 경우 select 값은 복수
    @Query("select b, count(distinct r), count(distinct f) " +
            "from Board b left join Reply r on b=r.board " +
            "left join Favorite f on f.board = b " +
            "group by b order by b.dno desc ")
    Page<Object[]> getData1(Pageable pageable);



}
