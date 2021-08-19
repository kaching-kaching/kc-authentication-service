package com.kaching.kcauthenticationservice.repository;

import com.kaching.kcauthenticationservice.dto.PermissionProjection;
import com.kaching.kcauthenticationservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@RepositoryRestResource(collectionResourceRel = "permissions", path = "permissions", excerptProjection = PermissionProjection.class)
public interface PermissionRepository extends JpaRepository<Permission, BigDecimal> {
}
