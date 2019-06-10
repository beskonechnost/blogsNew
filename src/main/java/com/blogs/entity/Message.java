package com.blogs.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Audited(withModifiedFlag = true)
@Table(name = "messages")
public class Message extends AbstractEntity {

    @Column(name = "MESSAGE_TEXT")
    @Length(max = 255, message = "MESSAGE_TEXT must be no more than 255 characters")
    @NotNull(message = "MESSAGE_TEXT must not be null or empty")
    private String messageText;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_MESSAGE_FROM_USER"))
    private User user;

}
