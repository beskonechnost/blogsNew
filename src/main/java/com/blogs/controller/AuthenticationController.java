package com.blogs.controller;

import com.blogs.dto.UserDto;
import com.blogs.entity.User;
import com.blogs.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> onLogin () {
        User user = userService.getCurrentUser();
        UserDto userToReturn = new UserDto(user);

        return new ResponseEntity<>(userToReturn, HttpStatus.OK);
    }

}
