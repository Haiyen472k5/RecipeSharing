package com.recipeshare.backend.Save;

import com.recipeshare.backend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SaveRepository extends JpaRepository<Save, Long> {
    List<Save> findByUser(User user); // Bài đã lưu
}
