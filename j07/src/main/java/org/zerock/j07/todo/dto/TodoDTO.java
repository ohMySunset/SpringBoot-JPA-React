package org.zerock.j07.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

    private Long tno;
    private String content;
    private boolean del;
    private LocalDateTime redData;
    private LocalDateTime modData;



}
