package org.zerock.j08.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

    private Long id;

    private String storeName;

    private String latitude;

    private String longitude;

    private String address;

    private boolean del;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
