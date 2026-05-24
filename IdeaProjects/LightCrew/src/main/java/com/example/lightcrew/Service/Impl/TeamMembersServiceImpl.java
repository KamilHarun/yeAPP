package com.example.lightcrew.Service.Impl;

import com.example.lightcrew.Mapper.TeamMemberMapper;
import com.example.lightcrew.Model.TeamMember;
import com.example.lightcrew.Repositories.TeamMembersRepository;
import com.example.lightcrew.Service.CloudinaryService;
import com.example.lightcrew.Service.TeamMembersService;
import com.example.lightcrew.dto.request.TeamMembersRequestDto;
import com.example.lightcrew.dto.response.TeamMembersResponseDto;
import com.example.lightcrew.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TeamMembersServiceImpl implements TeamMembersService {

    private final TeamMembersRepository teamMembersRepository;
    private final TeamMemberMapper teamMemberMapper;
    private final CloudinaryService service;
    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional(readOnly = true)
    public List<TeamMembersResponseDto> getAll() {
        log.info("Get all team members");
       return teamMembersRepository.findAllTeamMembers()
                .stream()
                .map(teamMemberMapper::toTeamMembersResponseDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TeamMembersResponseDto findById(Long id) {
        log.info("Get team member by id: {}", id);
       return teamMembersRepository.findById(id).map(teamMemberMapper::toTeamMembersResponseDto).
                orElseThrow(()->
                new ResourceNotFoundException("Resource :" , id)
                        );
    }

    @Override
    public TeamMembersResponseDto createMember(TeamMembersRequestDto request, MultipartFile file) {
        log.info("Create team member: {}", request);
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }
        // DTO → Entity
        TeamMember teamMember = teamMemberMapper.toTeamMember(request);

            String uploadedFileUrl = cloudinaryService.uploadFile(file);
            teamMember.setImageUrl(uploadedFileUrl);
        // Entity-i DB-yə save et
        TeamMember savedMember = teamMembersRepository.save(teamMember);

        // Entity → Response DTO
        return teamMemberMapper.toTeamMembersResponseDto(savedMember);
    }

    @Override
    public TeamMembersResponseDto updateMember(Long id, TeamMembersRequestDto request , MultipartFile file) {
        log.info("Update team member: {}", request);
        TeamMember exist = teamMembersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Resource :", id));

        if (file != null && !file.isEmpty()) {
            String uploadFile = cloudinaryService.uploadFile(file);
            exist.setImageUrl(uploadFile);
        }

        teamMemberMapper.updateTeamMember(exist, request);
        return teamMemberMapper.toTeamMembersResponseDto(teamMembersRepository.save(exist));
    }

    @Override
    public void deleteMember(Long id) {
        log.info("Delete team member: {}", id);
        TeamMember member = teamMembersRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Resource :", id));
        teamMembersRepository.delete(member);
    }
}