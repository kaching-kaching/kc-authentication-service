package com.kaching.kcauthenticationservice.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    protected Instant creationTimestamp;
    @UpdateTimestamp
    protected Instant updateTimestamp;
}
