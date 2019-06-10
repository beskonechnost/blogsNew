package com.blogs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MessageDto extends  AbstractDto {

    private String messageText;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a dd MMM yyyy")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a dd MMM yyyy")
    private LocalDateTime modifiedDate;

    private Long userId;
    private String username;
}
