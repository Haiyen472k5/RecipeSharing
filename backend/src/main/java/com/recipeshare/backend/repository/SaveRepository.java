package com.recipeshare.backend.repository;

import com.recipeshare.backend.entity.Save;
import com.recipeshare.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SaveRepository extends JpaRepository<Save, Long> {
    List<Save> findByUser(User user); // Bài đã lưu
}
