package org.zerock.j08.store.entity;

import lombok.*;
import org.zerock.j08.common.entity.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name = "tbl_str")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String storeName;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String address;

    private boolean del;

    public void changeName(String storeName) {

        this.storeName = storeName;
    }
}
