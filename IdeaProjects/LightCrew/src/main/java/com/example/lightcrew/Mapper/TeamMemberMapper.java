package com.example.lightcrew.Mapper;

import com.example.lightcrew.Model.TeamMember;
import com.example.lightcrew.dto.request.TeamMembersRequestDto;
import com.example.lightcrew.dto.response.TeamMembersResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapstructConfig.class)
public interface TeamMemberMapper {

    TeamMembersResponseDto toTeamMembersResponseDto(TeamMember teamMember);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "updatedAt" , ignore = true)
    TeamMember toTeamMember(TeamMembersRequestDto teamMembersRequestDto);


    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "updatedAt" , ignore = true)
    void updateTeamMember(@MappingTarget TeamMember teamMember , TeamMembersRequestDto teamMembersRequestDto);
}
