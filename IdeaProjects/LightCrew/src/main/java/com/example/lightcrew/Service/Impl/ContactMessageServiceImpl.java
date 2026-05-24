package com.example.lightcrew.Service.Impl;

import com.example.lightcrew.Mapper.ContactMapper;
import com.example.lightcrew.Model.Contact;
import com.example.lightcrew.Repositories.ContactMessageRepository;
import com.example.lightcrew.Service.ContactMessageService;
import com.example.lightcrew.dto.request.ContactRequestDto;
import com.example.lightcrew.dto.response.ContactResponseDto;
import com.example.lightcrew.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ContactMessageServiceImpl implements ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMapper contactMapper;

    @Override
    public ContactResponseDto createMessage(ContactRequestDto request) {
        log.info("New contact message from: {}", request.getEmail());
        Contact entity = contactMapper.toEntity(request);
        return contactMapper.toResponse(contactMessageRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactResponseDto> getAllMessages() {
        log.info(" Getting all contact Messages");
        return contactMessageRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(contactMapper::toResponse).toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<ContactResponseDto> getUnreadMessages() {
        log.info("Getting all contact Messages");
        return contactMessageRepository.findByIsReadFalseOrderByCreatedAtDesc()
                .stream()
                .map(contactMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContactResponseDto getMessagesById(Long id) {
        log.info("Getting all project Messages by ID: {}", id);
        return contactMessageRepository.findById(id).map(contactMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Message :", id));
    }

    @Override
    public ContactResponseDto markAsRead(Long id) {
        log.info("Marking contact with id: {}", id);
        Contact message = contactMessageRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Message", id));
        message.setRead(true);
        return contactMapper.toResponse(contactMessageRepository.save(message));
    }

    @Override
    public void deleteMessage(Long id) {
        log.info("Deleting contact with id: {}", id);
        Contact message = contactMessageRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Message", id));
        contactMessageRepository.delete(message);
    }
}
