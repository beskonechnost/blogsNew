package com.blogs.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "audit")
@EntityListeners({AuditingEntityListener.class})
public class AuditEntity extends AbstractEntity {

    @Column(name = "ACTION")
    private String action;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "ENTITY_ID")
    private String entityId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SUCCESSFUL")
    private Boolean isSuccessful = true;

    @Column(name = "DATE")
    private LocalDateTime date = LocalDateTime.now();

    public AuditEntity () {
    }

    public AuditEntity (String action, String userName) {
        this.action = action;
        this.userName = userName;
    }

    public AuditEntity (String action, String userName, Boolean isSuccessful) {
        this.action = action;
        this.userName = userName;
        this.isSuccessful = isSuccessful;
    }

}
