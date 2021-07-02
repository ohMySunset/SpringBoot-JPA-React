package org.zerock.j07.board.dto;

import lombok.Data;
import org.zerock.j07.common.dto.ListRequestDTO;

@Data
public class BoardRequestDTO extends ListRequestDTO {

    private String type;
}
