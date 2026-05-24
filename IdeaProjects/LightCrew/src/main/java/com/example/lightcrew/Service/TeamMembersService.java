package com.example.lightcrew.Service;

import com.example.lightcrew.Model.TeamMember;
import com.example.lightcrew.dto.request.TeamMembersRequestDto;
import com.example.lightcrew.dto.response.TeamMembersResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface TeamMembersService {

    List<TeamMembersResponseDto> getAll();

    TeamMembersResponseDto findById(Long id);

    TeamMembersResponseDto createMember(TeamMembersRequestDto request , MultipartFile file);

    TeamMembersResponseDto updateMember(Long id, TeamMembersRequestDto request , MultipartFile file);

    void deleteMember(Long id);


}
