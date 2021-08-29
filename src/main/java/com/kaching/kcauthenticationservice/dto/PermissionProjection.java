package com.kaching.kcauthenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaching.kcauthenticationservice.entity.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(types = Permission.class, name = "permissionProjection")
public interface PermissionProjection {

    @JsonProperty("id")
    @Value("#{target.id}")
    BigDecimal getId();

    @JsonProperty("name")
    @Value("#{target.name}")
    String getName();
}
