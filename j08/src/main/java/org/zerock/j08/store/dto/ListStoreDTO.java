package org.zerock.j08.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListStoreDTO {

    private StoreDTO dto;

    private long productCount;
}
