package com.example.lightcrew.API.publicAPI;

import com.example.lightcrew.Enum.ProjectType;
import com.example.lightcrew.Model.Project;
import com.example.lightcrew.Service.ContactMessageService;
import com.example.lightcrew.Service.ProjectService;
import com.example.lightcrew.Service.TeamMembersService;
import com.example.lightcrew.dto.request.ContactRequestDto;
import com.example.lightcrew.dto.response.ContactResponseDto;
import com.example.lightcrew.dto.response.ProjectResponseDto;
import com.example.lightcrew.dto.response.TeamMembersResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class PublicController {

    private final ProjectService projectService;
    private final TeamMembersService teamMembersService;
    private final ContactMessageService contactMessageService;

    // ─────────────────────────────────
    // PROJECTS
    // ─────────────────────────────────

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @GetMapping("/projects/type/{type}")
    public ResponseEntity<List<ProjectResponseDto>> getProjectsByType(
            @PathVariable ProjectType type) {
        return ResponseEntity.ok(projectService.getProjectByType(type));
    }

    // ─────────────────────────────────
    // TEAM
    // ─────────────────────────────────

    @GetMapping("/team")
    public ResponseEntity<List<TeamMembersResponseDto>> getAllTeamMembers() {
        return ResponseEntity.ok(teamMembersService.getAll());
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<TeamMembersResponseDto> getTeamMemberById(
            @PathVariable Long id) {
        return ResponseEntity.ok(teamMembersService.findById(id));
    }

    // ─────────────────────────────────
    // CONTACT —  POST (form submission)
    // ─────────────────────────────────

    @PostMapping("/contact")
    public ResponseEntity<ContactResponseDto> sendMessage(
            @Valid @RequestBody ContactRequestDto request) {
        return ResponseEntity.status(201).body(contactMessageService.createMessage(request));
    }
}


