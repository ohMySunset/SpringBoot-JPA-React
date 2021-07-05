package org.zerock.j09.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.j09.dto.FoodStoreDTO;
import org.zerock.j09.dto.ListFoodStoreDTO;
import org.zerock.j09.entity.FoodStore;
import org.zerock.j09.repository.FoodStoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class FoodStoreServiceImpl implements FoodStoreService {

    private final FoodStoreRepository storeRepository;

    @Override
    public Long register(FoodStoreDTO storeDTO) {

        FoodStore entity = dtoToEntity(storeDTO);

        FoodStore result = storeRepository.save(entity);

        return result.getFno();
    }

    @Override
    public List<ListFoodStoreDTO> getList() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("fno").descending());

        Page<Object[]> result = storeRepository.getList(pageable);

        return result.getContent().stream().map(arr-> arrToDTO(arr)).collect(Collectors.toList());
    }

}
