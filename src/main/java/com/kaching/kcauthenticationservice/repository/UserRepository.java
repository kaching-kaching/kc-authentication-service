package com.kaching.kcauthenticationservice.repository;

import com.kaching.kcauthenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {
    @Query("select (count(u) > 0) from User u where upper(u.email) = upper(:email)")
    boolean isEmailExist(@Param("email") String email);

}
