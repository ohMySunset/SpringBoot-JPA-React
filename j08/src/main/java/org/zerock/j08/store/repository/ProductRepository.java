package org.zerock.j08.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.j08.store.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
