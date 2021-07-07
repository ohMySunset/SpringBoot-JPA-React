package org.zerock.j08.store.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.j08.common.dto.ListRequestDTO;
import org.zerock.j08.common.dto.ListResponseDTO;
import org.zerock.j08.common.dto.PageMaker;
import org.zerock.j08.store.dto.ListStoreDTO;
import org.zerock.j08.store.dto.RequestStoreDTO;
import org.zerock.j08.store.dto.StoreDTO;
import org.zerock.j08.store.repository.StoreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public ListResponseDTO<ListStoreDTO> getList(RequestStoreDTO requestStoreDTO) {

        Pageable pageable = PageRequest.of(0, 10);

        Page<Object[]> result = storeRepository.getSearchList(requestStoreDTO.getKeyword(), requestStoreDTO.getType(), requestStoreDTO.getPageable());

        // result.getContent().forEach(arr->log.info(Arrays.toString(arr)));

        List<ListStoreDTO> dtoList = result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(requestStoreDTO.getPage(), requestStoreDTO.getSize(), (int) result.getTotalElements());

        return ListResponseDTO.<ListStoreDTO>builder().listRequestDTO(requestStoreDTO).dtoList(dtoList).pageMaker(pageMaker).build();


    }
}
