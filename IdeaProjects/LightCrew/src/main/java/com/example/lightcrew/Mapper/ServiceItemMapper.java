package com.example.lightcrew.Mapper;

import com.example.lightcrew.Model.ServiceItem;
import com.example.lightcrew.dto.request.ServiceItemRequestDto;
import com.example.lightcrew.dto.response.ServiceItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapstructConfig.class)
public interface ServiceItemMapper {


    ServiceItemResponseDto torResponse (ServiceItem serviceItem);


    //toEntity
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "updatedAt" , ignore = true)
    ServiceItem toEntity(ServiceItemRequestDto serviceItemRequestDto);

       /////updateEntity
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "updatedAt" , ignore = true)
    void updateEntity(@MappingTarget  ServiceItem serviceItem , ServiceItemRequestDto serviceItemRequestDto);



}
