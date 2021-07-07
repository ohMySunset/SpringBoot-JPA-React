package org.zerock.j08.storetests;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j08.store.entity.Store;
import org.zerock.j08.store.repository.StoreRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class StoreTests {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void test1() {
        log.info(storeRepository.getClass().getName());
    }

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Store store = Store.builder().storeName(i + "호 찐맛집")
                    .latitude(i + "23.456.1")
                    .longitude("12" + i + ".765." + i)
                    .address("서울시" + i + "5-2" + i)
                    .build();

            storeRepository.save(store);

        });
    }

    @Test
    public void testSelect() {
        Optional<Store> result = storeRepository.findById(100L);
        result.ifPresent(store -> log.info("299번째 집 : " + store.getStoreName()));
    }

    @Test
    public void testUpdate() {
        Optional<Store> result = storeRepository.findById(100L);

        // 만약에 존재한다면
        result.ifPresent(store -> {
            store.changeName("100번째 찐찐찐맛집");
            storeRepository.save(store);
        });
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Store> page = storeRepository.findAll(pageable);

        log.info(page);

        page.getContent().forEach(result -> log.info("페이지 결과 : " + result));
        log.info("전체페이지 수 : " + page.getTotalPages());
    }

    @Test
    public void testDelete() {
        storeRepository.delete(Store.builder().id(10L).build());
    }

}
