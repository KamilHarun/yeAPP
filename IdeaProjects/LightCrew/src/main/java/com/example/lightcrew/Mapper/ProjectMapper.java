package com.example.lightcrew.Mapper;

import com.example.lightcrew.Model.Project;
import com.example.lightcrew.dto.request.ProjectRequestDto;
import com.example.lightcrew.dto.response.ProjectResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(config = MapstructConfig.class)
public interface ProjectMapper {

    // toResponse — heç nə yazmırıq cunki ResponseDtoda adlar eynidir
    ProjectResponseDto toResponse(Project project);

    // toEntity — yalnız server yaradan field-ləri ignore edirik
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Project toEntity(ProjectRequestDto dto);

    // updateEntity — eyni
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(@MappingTarget Project existing, ProjectRequestDto dto);
}



