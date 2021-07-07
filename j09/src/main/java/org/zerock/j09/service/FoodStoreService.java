package org.zerock.j09.service;

import org.zerock.j09.dto.FoodStoreDTO;
import org.zerock.j09.dto.ListFoodStoreDTO;
import org.zerock.j09.entity.FoodStore;
import org.zerock.j09.entity.FoodStoreImage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface FoodStoreService {

    Long register(FoodStoreDTO storeDTO);

    List<ListFoodStoreDTO> getList();

    /**
     * DTO -> Entity으로 변환
     *
     * @param storeDTO
     * @return FoodStore
     */

    default FoodStore dtoToEntity(FoodStoreDTO storeDTO) {
        Set<FoodStoreImage> imageSet = storeDTO.getImageList().stream()
                .map(imageDto -> FoodStoreImage.builder()
                        .uuid(imageDto.getUuid())
                        .fileName(imageDto.getFileName())
                        .main(imageDto.isMain())
                        .build())
                .collect(Collectors.toSet());

        return FoodStore.builder().fno(storeDTO.getFno()).fname(storeDTO.getFname()).storeImages(imageSet).build();
    }

    default ListFoodStoreDTO arrToDTO(Object[] arr) {

        return ListFoodStoreDTO.builder()
                .fno((Long) arr[0])
                .fname((String) arr[1])
                .uuid((String) arr[2])
                .fileName((String) arr[3])
                .build();

    }
}
