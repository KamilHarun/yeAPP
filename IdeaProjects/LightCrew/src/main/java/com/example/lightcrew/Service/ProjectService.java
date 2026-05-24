package com.example.lightcrew.Service;

import com.example.lightcrew.Enum.ProjectType;
import com.example.lightcrew.Model.Project;
import com.example.lightcrew.dto.request.ProjectRequestDto;
import com.example.lightcrew.dto.response.ProjectResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {


    List<ProjectResponseDto> getAllProjects();

    ProjectResponseDto findById(Long id);

    List<ProjectResponseDto> getProjectByType(ProjectType type);

    ProjectResponseDto createProject(@Valid ProjectRequestDto request);

    ProjectResponseDto updateProject(Long id, @Valid ProjectRequestDto request);

    void deleteProject(Long id);



}
