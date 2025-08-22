package org.example.userms.Config;

import org.example.userms.Dto.request.AddressRequestDto;
import org.example.userms.Dto.response.AddressResponseDto;
import org.example.userms.Model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Address toEntity(AddressRequestDto dto);

    AddressResponseDto toDto(Address address);
    List<AddressRequestDto> toDtoList(List<Address> addresses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateAddressFromDto(Address address, AddressRequestDto dto);
}