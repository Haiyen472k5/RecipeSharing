package org.example.recipes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.example.recipes.exception.UsernameExistsException;
import org.example.recipes.Users;
import org.example.recipes.login.AuthService;
import org.example.recipes.login.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private Users user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        user = new Users();
        user.setUsername("alice");
        user.setEmail("alice@example.com");
        user.setPassword("$2a$10$hash…"); // giả lập mật khẩu mã hoá
    }

    @Test
    void loginByUsername_success() {
        when(userRepo.findByUsernameOrEmail("alice","alice")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("raw", user.getPassword())).thenReturn(true);

        boolean result = authService.login("alice","raw");
        assertThat(result).isTrue();
    }

    @Test
    void loginByEmail_success() {
        when(userRepo.findByUsernameOrEmail("alice@example.com","alice@example.com"))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches("raw", user.getPassword())).thenReturn(true);

        boolean result = authService.login("alice@example.com","raw");
        assertThat(result).isTrue();
    }

    @Test
    void login_notFound_returnsFalse() {
        when(userRepo.findByUsernameOrEmail(anyString(), anyString()))
                .thenReturn(Optional.empty());

        boolean result = authService.login("unknown","whatever");
        assertThat(result).isFalse();
    }

    @Test
    void login_wrongPassword_returnsFalse() {
        when(userRepo.findByUsernameOrEmail("alice","alice"))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches("bad","$2a$10$hash…")).thenReturn(false);

        boolean result = authService.login("alice","bad");
        assertThat(result).isFalse();
    }

    @Test
    void register_newUser_succeeds() {
        when(userRepo.existsByUsername("bob")).thenReturn(false);
        when(userRepo.existsByEmail("b@e.com")).thenReturn(false);
        // Không ném exception → OK
        authService.register("bob","b@e.com","rawPass");
        // verify save được gọi
        Mockito.verify(userRepo).save(ArgumentMatchers.any(Users.class));
    }

    @Test
    void register_duplicateUsername_throws() {
        when(userRepo.existsByUsername("alice")).thenReturn(true);
        org.junit.jupiter.api.Assertions.assertThrows(
                UsernameExistsException.class,
                () -> authService.register("alice","x@y.com","pwd")
        );
    }

    // Tương tự bạn có thể thêm test cho EmailExistsException nếu dùng
}
