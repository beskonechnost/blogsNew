package com.blogs.dto;

import com.blogs.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto extends AbstractDto {

    private String username;
    private String password;
    private String role;
    private boolean enabled;

    public UserDto (User user) {
        this.setId(user.getId());
        this.username = user.getUsername();
        this.role = user.getRole();
    }

}
