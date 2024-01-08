package com.xpresspayments.api.model.repository;

import com.xpresspayments.api.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByContact_EmailAddress(String emailAddress);
    Optional<User> findUserById(Long userId);
    boolean existsByContact_EmailAddress(String emailAddress);
}
