package com.example.lightcrew.Mapper;

import com.example.lightcrew.Model.Contact;
import com.example.lightcrew.dto.request.ContactRequestDto;
import com.example.lightcrew.dto.response.ContactResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface ContactMapper {


    ContactResponseDto toResponse(Contact message);

    // toEntity — server yaradan + olmayan field-ləri ignore edirik
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "isRead", constant = "false")
    Contact toEntity(ContactRequestDto dto);

}

