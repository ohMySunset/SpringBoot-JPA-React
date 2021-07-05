package org.zerock.j09.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.j09.entity.FoodMenu;
import org.zerock.j09.entity.FoodStore;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
public class FoodStoreRepoTests {

    @Autowired
    private FoodStoreRepository storeRepository;

//    @Test
//    public void testRegister(){
//
//        FoodStore store = FoodStore.builder()
//                .fname("store1")
//                .build();
//
//        store.addMenu(FoodMenu.builder().mname("떡볶이").build());
//        store.addMenu(FoodMenu.builder().mname("라면").build());
//
//        // 영속성 전이 -> FoodStore가 context영역에서 처리될 때 FoodMenu도 함께 처리되도록 하는것.
//        storeRepository.save(store);
//    }
//
//
//    @Test
//    public void testRead2(){
//
//        FoodStore result = storeRepository.getById(2L);
//
//        System.out.println(result.getFname());
//        System.out.println(result.getFoodMenus());
//    }
//
//    @Test
//    @Transactional
//    public void testRead(){
//
//        Optional<FoodStore> result = storeRepository.findById(2L);
//
//        if(result.isPresent()){
//
//            FoodStore store = result.get();
//            System.out.println(store.getFname());
//            System.out.println(store.getFoodMenus());
//            // Lazy로딩이 기본값이기 때문에 noSession에러가 발생 -> @Transacntional을 걸어주면 해결은 되나 쿼리가 2번 날라감
//
//        }//end if
//    }
//
//    @Test
//    public void testRemoveMenu(){
//
//        FoodStore store = storeRepository.getById(2L);
//
//        store.removeMenu(1L);  // 영속성 컨텍스트 영역에서 한 개를 빼버리면 orphanRemoval = true 설정을 통해
//                                       // DB와 동기화되면서 필요없는 데이터로 인식되어 삭제처리됨.
//
//        storeRepository.save(store);
//
//    }


}
