package com.blogs.mapper;

import com.blogs.dto.MessageDto;
import com.blogs.entity.Message;
import com.blogs.service.MessageService;
import com.blogs.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MessageMapper  {

    private final MessageService messageService;
    private final UserService userService;

    public MessageDto map (Message entity) {
        MessageDto dto = new MessageDto();

        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setMessageText(entity.getMessageText());
        dto.setUserId(entity.getUser().getId());
        dto.setUsername(entity.getUser().getUsername());

        return dto;
    }

    public Message map (MessageDto dto) {
        Message entity = messageService.findById(dto.getId()).orElse(new Message());

        if(!Optional.ofNullable(entity.getCreateDate()).isPresent()) {
            entity.setCreateDate(LocalDateTime.now());
        }

        entity.setModifiedDate(LocalDateTime.now());
        entity.setMessageText(dto.getMessageText());

        if(!Optional.ofNullable(entity.getUser()).isPresent()) {
            entity.setUser(userService.getCurrentUser());
        }

        return entity;
    }



}
