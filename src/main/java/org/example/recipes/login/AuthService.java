package org.example.recipes.login;

import java.util.Optional;
import org.example.recipes.exception.EmailExistsException;
import org.example.recipes.exception.UsernameExistsException;
import org.example.recipes.user.UserRepository;
import org.example.recipes.user.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final IdGeneratorService idGenerator;

    public AuthService(UserRepository userRepo,
                       PasswordEncoder passwordEncoder,
                       IdGeneratorService idGenerator) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.idGenerator = idGenerator;
    }

    /**
     * Kiểm tra đăng nhập bằng username hoặc email.
     * @param principal username hoặc email
     * @param rawPassword mật khẩu thuần
     * @return true nếu đăng nhập hợp lệ, false nếu không
     */
    public boolean login(String principal, String rawPassword) {
        Optional<Users> userOpt = userRepo.findByUsernameOrEmail(principal, principal);
        if (userOpt.isEmpty()) {
            return false;
        }
        Users user = userOpt.get();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    /**
     * Đăng ký user mới. Ném UsernameExistsException hoặc EmailExistsException nếu trùng.
     */
    @Transactional
    public void register(String username, String email, String rawPassword) {
        if (userRepo.existsByUsername(username)) {
            throw new UsernameExistsException();
        }
        if (userRepo.existsByEmail(email)) {
            throw new EmailExistsException();
        }
        // sinh ID mới
        String newId = idGenerator.generateId();

        Users u = new Users();
        u.setId(newId);
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(rawPassword));
        userRepo.save(u);
    }
}
