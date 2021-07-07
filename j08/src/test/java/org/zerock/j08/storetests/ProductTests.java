package org.zerock.j08.storetests;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j08.store.entity.Product;
import org.zerock.j08.store.repository.ProductRepository;
import org.zerock.j08.store.entity.Store;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class ProductTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertTest() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            long sno = (int) (Math.random() * 200) + 1;

            Store store = Store.builder().id(sno).build();

            Product product = Product.builder().productName("제품" + i).store(store).build();

            productRepository.save(product);


        });

    }
}
