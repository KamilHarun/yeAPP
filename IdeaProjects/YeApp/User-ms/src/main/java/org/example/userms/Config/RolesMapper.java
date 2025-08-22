package org.example.userms.Config;

import org.example.userms.Dto.request.RolesRequestDto;
import org.example.userms.Dto.response.RolesResponseDto;
import org.example.userms.Model.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolesMapper {

    RolesMapper  MAPPER = Mappers.getMapper(RolesMapper.class);

    RolesResponseDto rolesToResponseDto(Roles roles);
    Roles requestDtoToRoles(RolesRequestDto rolesRequestDto);
    RolesRequestDto rolesToRequestDto(Roles roles);

}
