package org.zerock.j09.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodStoreImageDTO {

    private String uuid;

    private String fileName;

    private boolean main;

}
