package org.example.userms.Service;

import org.example.userms.Dto.request.RolesRequestDto;
import org.example.userms.Dto.response.RolesResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolesService {
    Long createRole(RolesRequestDto rolesRequestDto);

    RolesResponseDto findById(Long id);

    List<RolesResponseDto> getAll();

    void deleteRole(RolesRequestDto rolesRequestDto);

}
