package com.example.lightcrew.Repositories;

import com.example.lightcrew.Enum.ProjectType;
import com.example.lightcrew.Model.Project;
import com.example.lightcrew.dto.response.ProjectResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByType(ProjectType type);

    List<Project> findAllByOrderByOrderIndexAsc();

    Collection<Project> findByTypeOrderByOrderIndexAsc(ProjectType type);
}
