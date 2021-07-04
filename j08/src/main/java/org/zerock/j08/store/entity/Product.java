package org.zerock.j08.store.entity;

import lombok.*;
import org.zerock.j08.common.entity.BaseEntity;
import org.zerock.j08.store.entity.Store;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbl_product")
@ToString(exclude = "store")
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    @Column(nullable = false, length = 300)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
}
