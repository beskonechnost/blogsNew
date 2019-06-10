package com.blogs.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_USERNAME", columnNames = {"username"})
        }
)
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "USERNAME", nullable = false)
    @Length(max = 50, message = "USERNAME must be no more than 50 characters")
    @NotBlank(message = "USERNAME must not be null or empty")
    private String username;

    @Column(name = "PASSWORD")
    @Length(max = 100, message = "PASSWORD must be no more than 100 characters")
    @NotBlank(message = "PASSWORD must not be null or empty")
    private String password;

    @Column(name = "ROLE")
    @Length(max = 12, message = "ROLE must be no more than 12 characters")
    @NotBlank(message = "ROLE must not be null or empty")
    private String role = "PUBLISHER";

    @Column(name = "ENABLED", nullable = false)
    @NotBlank(message = "ENABLED must not be null or empty")
    private boolean enabled = true;

    @Override
    public boolean equals (Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("PUBLISHER");
        authorities.add(authority);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
