package com.example.lightcrew.Service;

import com.example.lightcrew.Model.Contact;
import com.example.lightcrew.dto.request.ContactRequestDto;
import com.example.lightcrew.dto.response.ContactResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactMessageService {


    ContactResponseDto createMessage(@Valid ContactRequestDto request);

    List<ContactResponseDto> getAllMessages();

    List<ContactResponseDto> getUnreadMessages();


    ContactResponseDto getMessagesById(Long id);

    ContactResponseDto markAsRead(Long id);

    void deleteMessage(Long id);
}
