package org.zerock.j09.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

    // uuid : 범용 고유 식별자
    private String uuid;

    private String fileName;


}
