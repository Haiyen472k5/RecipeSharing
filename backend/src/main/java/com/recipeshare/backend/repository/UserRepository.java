package com.recipeshare.backend.repository;

import com.recipeshare.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Tìm theo username (để hiển thị profile)
    Optional<User> findByUsername(String username);
}
