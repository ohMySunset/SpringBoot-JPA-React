package org.zerock.j09.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FoodStoreImage {

    @Id
    private String uuid;

    private String fileName;

    private boolean main;

}
