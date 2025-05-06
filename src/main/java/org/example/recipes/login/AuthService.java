package org.example.recipes.login;

import java.util.Optional;

import org.example.recipes.Exception.UsernameExistException;
import org.example.recipes.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Kiểm tra đăng nhập với username và mật khẩu thô (rawPassword).
     * @return true nếu hợp lệ, false nếu không.
     */
    public boolean login(String username, String rawPassword) {
        Optional<Users> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()) {
            return false;
        }
        Users user = userOpt.get();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    /**
     * Đăng ký user mới. Ném UsernameExistsException nếu trùng.
     */
    @Transactional
    public void register(String username, String email, String rawPassword) throws UsernameExistException {
        if (userRepo.existsByUsername(username)) {
            throw new UsernameExistException("Username '" + username + "' đã tồn tại.");
        }
        Users u = new Users();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(rawPassword));
        userRepo.save(u);
    }
}