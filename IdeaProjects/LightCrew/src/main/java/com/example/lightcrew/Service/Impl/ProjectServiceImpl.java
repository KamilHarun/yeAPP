package com.example.lightcrew.Service.Impl;

import com.example.lightcrew.Enum.ProjectType;
import com.example.lightcrew.Mapper.ProjectMapper;
import com.example.lightcrew.Model.Project;
import com.example.lightcrew.Repositories.ProjectRepository;
import com.example.lightcrew.Service.ProjectService;
import com.example.lightcrew.dto.request.ProjectRequestDto;
import com.example.lightcrew.dto.response.ProjectResponseDto;
import com.example.lightcrew.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponseDto> getAllProjects() {
        log.info("Fetching all projects");
        return projectRepository.findAllByOrderByOrderIndexAsc()
                .stream()
                .map(projectMapper::toResponse)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public ProjectResponseDto findById(Long id) {
        log.info("Fetching project by id: {}", id);
        return projectRepository.findById(id)
                .map(projectMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Project", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponseDto> getProjectByType(ProjectType type) {
        if (type == null) {
            log.info("Fetching all projects by type");
            throw new IllegalArgumentException("Type cannot be null");

        }
        log.info("Fetching project by type: {}", type);
        return projectRepository.findByTypeOrderByOrderIndexAsc(type)
                .stream()
                .map(projectMapper::toResponse)
                .toList();
    }

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto request) {
        log.info("Creating new project: {}", request);
        Project project = projectMapper.toEntity(request);
        project.setActive(true);
        return projectMapper.toResponse(projectRepository.save(project));

    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto request) {
        log.info("Updating project: {}", id);
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", id));
        projectMapper.updateEntity(existing, request);
        return projectMapper.toResponse(projectRepository.save(existing));
    }

    @Override
    public void deleteProject(Long id) {
        log.info("Deleting project: {}", id);
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", id));
        projectRepository.delete(existing);
    }
}