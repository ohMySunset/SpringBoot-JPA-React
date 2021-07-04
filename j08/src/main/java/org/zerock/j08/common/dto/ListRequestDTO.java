package org.zerock.j08.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String keyword;

    public int getPage() {
        return page>0? page:1 ;
    }

    public int getSize() {
        return size<10? size: 10;
    }

    @JsonIgnore
    public Pageable getPageable(){
        return PageRequest.of(page-1, size);
    }
}
