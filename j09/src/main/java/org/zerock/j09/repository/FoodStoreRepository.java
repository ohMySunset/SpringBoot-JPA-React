package org.zerock.j09.repository;

import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j09.entity.FoodStore;

public interface FoodStoreRepository extends JpaRepository<FoodStore, Long> {

    //@OneToMany관계에서는 엔티티마다 Repository를 생성하지 않고 하나로 관리한다.

    //attributePaths -> 변수명
    @EntityGraph(attributePaths = "foodMenus", type = EntityGraph.EntityGraphType.FETCH)
    @Query("select s from FoodStore s where s.fno = :sno")
    FoodStore getById(Long sno);

    // 목록을 가져올때는 limit처리가 잘 되고 있는지 꼭 확인할 것.
    @Query("select s.fno, s.fname, si.uuid, si.fileName " +
            " from FoodStore s left join s.storeImages si " +
            " where si.main =true and s.fno > 0 group by s, si ")
    Page<Object[]> getList(Pageable pageable);


}
