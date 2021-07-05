package org.zerock.j09.dto;

import lombok.*;
import org.zerock.j09.entity.FoodStoreImage;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodStoreDTO {

    private Long fno;

    private String fname;

    private List<FoodStoreImage> imageList;

}
