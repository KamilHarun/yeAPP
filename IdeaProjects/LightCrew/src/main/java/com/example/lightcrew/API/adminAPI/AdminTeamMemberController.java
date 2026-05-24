package com.example.lightcrew.API.adminAPI;

import com.example.lightcrew.Service.TeamMembersService;
import com.example.lightcrew.dto.request.TeamMembersRequestDto;
import com.example.lightcrew.dto.response.TeamMembersResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/team")
@RequiredArgsConstructor
public class AdminTeamMemberController {

    private final TeamMembersService teamMembersService;

    @GetMapping
    public ResponseEntity<List<TeamMembersResponseDto>> getAllMembers() {
        return ResponseEntity.ok(teamMembersService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamMembersResponseDto> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(teamMembersService.findById(id));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<TeamMembersResponseDto> createMember(
             @RequestPart("data") TeamMembersRequestDto request,
             @RequestPart("file") MultipartFile file) {
        return ResponseEntity.status(201).body(teamMembersService.createMember(request , file));
    }

    @PutMapping(value = "/members/{id}", consumes = "multipart/form-data")
    public ResponseEntity<TeamMembersResponseDto> updateMember(
            @PathVariable Long id,
            @RequestPart("data") TeamMembersRequestDto request,
            @RequestPart(value = "file", required = false) MultipartFile file  // optional
    ) {
        TeamMembersResponseDto response = teamMembersService.updateMember(id, request, file);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        teamMembersService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
