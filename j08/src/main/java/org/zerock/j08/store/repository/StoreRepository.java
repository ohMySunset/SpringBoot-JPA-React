package org.zerock.j08.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j08.store.entity.Store;
import org.zerock.j08.store.repository.dynamic.StoreSearch;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreSearch {

    @Query("select s, count(distinct p) from Store s left join Product p on p.store=s group by s order by s.id ")
    Page<Object[]> getList(Pageable pageable);
}
