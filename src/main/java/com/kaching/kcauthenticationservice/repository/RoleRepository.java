package com.kaching.kcauthenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RoleRepository extends JpaRepository<RoleRepository, BigDecimal> {
}
