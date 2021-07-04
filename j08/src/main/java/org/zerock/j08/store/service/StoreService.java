package org.zerock.j08.store.service;

import org.zerock.j08.common.dto.ListResponseDTO;
import org.zerock.j08.store.dto.ListStoreDTO;
import org.zerock.j08.store.dto.RequestStoreDTO;
import org.zerock.j08.store.dto.StoreDTO;
import org.zerock.j08.store.entity.Store;

public interface StoreService {

    ListResponseDTO<ListStoreDTO> getList(RequestStoreDTO requestStoreDTO);

    default Store dtoToEntity(StoreDTO dto){

        return Store.builder()
                .id(dto.getId())
                .storeName(dto.getStoreName())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .del(dto.isDel())
                .build();

    }

    default StoreDTO entityToDTO(Store store){
        return StoreDTO.builder()
                .id(store.getId())
                .storeName(store.getStoreName())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .address(store.getAddress())
                .del(store.isDel())
                .build();
    }

    default ListStoreDTO arrToDTO(Object[] arr){

        Store store = (Store) arr[0];
        long productCount = (long) arr[1];


        return ListStoreDTO.builder()
                .dto(entityToDTO(store))
                .productCount(productCount)
                .build();

    }


}
