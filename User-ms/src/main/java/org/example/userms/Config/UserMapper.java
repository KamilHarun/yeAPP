package org.example.userms.Config;

import org.example.userms.Dto.request.CreateUserDto;
import org.example.userms.Dto.response.UserResponseDto;
import org.example.userms.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestDtoToUser (CreateUserDto createUserDto);
    CreateUserDto userToUserRequestDto (User user);

    User responseDtoToUser (UserResponseDto userResponseDto);
    UserResponseDto userToUserResponseDto (User user);



}
