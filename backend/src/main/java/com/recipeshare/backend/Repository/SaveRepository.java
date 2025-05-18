package com.recipeshare.backend.Repository;

import com.recipeshare.backend.Entity.Save;
import com.recipeshare.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SaveRepository extends JpaRepository<Save, Long> {
    List<Save> findByUser(User user); // Bài đã lưu
}
