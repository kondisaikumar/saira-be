package com.saira.sairarestful.repository;

import com.saira.sairarestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return an Optional containing the user if found, or empty if not
     */
    Optional<User> findByEmail(String email);
}
