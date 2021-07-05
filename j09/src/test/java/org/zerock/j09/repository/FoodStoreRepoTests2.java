package org.zerock.j09.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j09.entity.FoodStore;

import java.util.Arrays;

@SpringBootTest
public class FoodStoreRepoTests2 {

    @Autowired
    private FoodStoreRepository foodStoreRepository;

    @Test
    public void testList(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("fno").descending());

        Page<Object[]> result = foodStoreRepository.getList(pageable);

        result.getContent().forEach(arr-> System.out.println(Arrays.toString(arr)));

    }







}
