package org.example.userms.Service.Impl;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.example.userms.Config.RolesMapper;
import org.example.userms.Dto.request.RolesRequestDto;
import org.example.userms.Dto.response.RolesResponseDto;
import org.example.userms.Model.Roles;
import org.example.userms.Repository.RolesRepository;
import org.example.userms.Service.RolesService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;
    private final RolesMapper rolesMapper;

    @Override
    @Transactional
    public Long createRole(RolesRequestDto dto) {
        log.debug("Request to create Role: {}", dto);

        if (dto == null || dto.getName() == null || dto.getName().isBlank()) {
            log.error("Invalid role create request: {}", dto);
            throw new IllegalArgumentException("Role name must not be null or blank");
        }

        // Check if role already exists
        rolesRepository.findByNameIgnoreCase(dto.getName())
                .ifPresent(r -> {
                    throw new DuplicateKeyException("Role with name '" + dto.getName() + "' already exists");
                });

        // Convert DTO to Entity
        Roles roles = rolesMapper.requestDtoToRoles(dto);

        // Save
        Roles saved = rolesRepository.save(roles);
        log.info("Role created successfully with id: {}", saved.getId());

        return saved.getId();
    }

    @Override
    public RolesResponseDto findById(Long id) {
        if (id == null) {
            log.error("Invalid role request ID: {}", id);
            throw new IllegalArgumentException("Invalid role request ID: " + id);
        }
        Roles roles = rolesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role with id " + id + " not found"));
        log.info("Role found successfully with id: {}", roles.getId());

        return rolesMapper.rolesToResponseDto(roles);


    }

    @Override
    public List<RolesResponseDto> getAll() {
        log.debug("Request to get all Roles");
        List<Roles> allRoles = rolesRepository.findAll();
        log.info("All Roles found successfully");
        if (allRoles.isEmpty()) {
            log.error("No Roles found");
            Collections.emptyList();
        }

        return allRoles
                .stream()
                .map(rolesMapper::rolesToResponseDto)
                .toList();

    }

    @Override
    public void deleteRole(RolesRequestDto rolesRequestDto) {
        rolesRepository.findById(rolesRequestDto.getId()).orElseThrow(()->
                new NotFoundException("Role with id " + rolesRequestDto.getId() + " not found"));
    rolesRepository.deleteById(rolesRequestDto.getId());}

}





