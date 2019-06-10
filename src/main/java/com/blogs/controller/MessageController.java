package com.blogs.controller;

import com.blogs.dto.MessageDto;
import com.blogs.entity.Message;
import com.blogs.exception.MessageNotFoundException;
import com.blogs.mapper.MessageMapper;
import com.blogs.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/message")
public class MessageController {

    private MessageMapper messageMapper;
    private MessageService messageService;

    public MessageController(MessageMapper messageMapper, MessageService messageService) {
        this.messageMapper = messageMapper;
        this.messageService = messageService;
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<MessageDto> save (@RequestBody MessageDto message) {
        return new ResponseEntity<>(messageMapper.map(findAndUpdate(message)), HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping
    public ResponseEntity<MessageDto> update (@RequestBody MessageDto message) {
        return new ResponseEntity<>(messageMapper.map(findAndUpdate(message)), HttpStatus.OK);
    }

    @ResponseBody
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Message entity = messageService.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        messageService.delete(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<List<MessageDto>> list () {
        List<MessageDto> list = new ArrayList<>();
        if(!messageService.findAll().isEmpty()) {
            list = messageService.findAll().stream().map(messageMapper::map).collect(Collectors.toList());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<MessageDto>> list (@PathVariable(name = "userId") Long userId) {
        List<MessageDto> list = new ArrayList<>();
        if(userId != null && !messageService.findAllByUserId(userId).isEmpty()) {
            list = messageService.findAllByUserId(userId).stream().map(messageMapper::map).collect(Collectors.toList());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private Message findAndUpdate (MessageDto dto) {
        Message entity = messageMapper.map(dto);
        return messageService.save(entity);
    }

}
