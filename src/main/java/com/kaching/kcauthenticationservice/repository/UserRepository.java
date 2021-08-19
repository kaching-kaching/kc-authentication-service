package com.kaching.kcauthenticationservice.repository;

import com.kaching.kcauthenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {
}
