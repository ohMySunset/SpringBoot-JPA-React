package org.zerock.j08.store.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreSearch {

    Page<Object[]> getSearchList(String keyword, String type, Pageable pageable);
}
