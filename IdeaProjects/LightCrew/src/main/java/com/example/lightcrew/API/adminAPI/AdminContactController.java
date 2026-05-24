package com.example.lightcrew.API.adminAPI;

import com.example.lightcrew.Service.ContactMessageService;
import com.example.lightcrew.dto.response.ContactResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class AdminContactController {

    private final ContactMessageService contactMessageService;


    @GetMapping("/getAllMessages")
    public ResponseEntity<List<ContactResponseDto>> getAllMessages() {
        return new ResponseEntity<>(contactMessageService.getAllMessages(), OK);
    }

    @GetMapping("/getUnreadMessages")
    public ResponseEntity<List<ContactResponseDto>> getUnreadMessages() {
        return ResponseEntity.ok(contactMessageService.getUnreadMessages());
    }

    @GetMapping("{/id}")
    public ResponseEntity<ContactResponseDto> getMessagesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(contactMessageService.getMessagesById(id));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDto> markAsRead(@PathVariable("id") Long id) {
        return ResponseEntity.ok(contactMessageService.markAsRead(id));
    }

    @DeleteMapping("{id}")
    public void deleteMessagesById(@PathVariable("id") Long id) {
        contactMessageService.deleteMessage(id);

    }

}