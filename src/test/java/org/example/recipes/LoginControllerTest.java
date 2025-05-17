// src/test/java/org/example/recipes/LoginControllerTest.java
package org.example.recipes;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import org.example.recipes.exception.GlobalExceptionHandler;
import org.example.recipes.exception.UsernameExistsException;
import org.example.recipes.login.AuthService;
import org.example.recipes.controller.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({
        LoginController.class,
        GlobalExceptionHandler.class,
        LoginControllerTest.TestConfig.class
})
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @BeforeEach
    void resetMock() {
        // Xoá mọi stub cũ để tránh ảnh hưởng giữa các test
        reset(authService);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public AuthService authService() {
            return mock(AuthService.class);
        }
    }

    @Test
    void loginPageContainsLinkToRegister() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("href=\"/auth/register\"")));
    }

    @Test
    void registerPageContainsLinkToLogin() throws Exception {
        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("href=\"/auth/login\"")));
    }

    @Test
    void showLoginPage_shouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login"))
                .andExpect(model().attributeExists("loginForm"));
    }

    @Test
    void showRegisterPage_shouldReturnRegisterView() throws Exception {
        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/register"))
                .andExpect(model().attributeExists("registerForm"));
    }

    @Test
    void whenCorrectCredentials_thenRedirectToHome() throws Exception {
        when(authService.login("user1", "pass1")).thenReturn(true);
        mockMvc.perform(post("/auth/login")
                        .param("username", "user1")
                        .param("password", "pass1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void whenInvalidCredentials_thenShowLoginWithError() throws Exception {
        when(authService.login(anyString(), anyString())).thenReturn(false);
        mockMvc.perform(post("/auth/login")
                        .param("username", "wrong")
                        .param("password", "wrong"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login"))
                .andExpect(model().attributeExists("loginError"));
    }

    @Test
    void whenLoginServiceThrowsException_thenStatus500() throws Exception {
        when(authService.login(anyString(), anyString()))
                .thenThrow(new RuntimeException("unexpected"));
        mockMvc.perform(post("/auth/login")
                        .param("username", "err")
                        .param("password", "err"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Lỗi hệ thống: unexpected"));
    }

    @Test
    void whenValidRegistration_thenRedirectToLoginWithFlag() throws Exception {
        // Không stub, mockService.register mặc định no-op
        mockMvc.perform(post("/auth/register")
                        .param("username", "newuser")
                        .param("email", "n@e.com")
                        .param("password", "pwd123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/login?registered"));
    }

    @Test
    void whenUsernameExists_thenShowRegisterWithError() throws Exception {
        // Chỉ stub đúng bộ parameters
        doThrow(new UsernameExistsException("Username đã tồn tại."))
                .when(authService)
                .register(eq("dup"), eq("d@up.com"), eq("pwd"));

        mockMvc.perform(post("/auth/register")
                        .param("username", "dup")
                        .param("email", "d@up.com")
                        .param("password", "pwd"))
                .andExpect(status().isOk())  // catch trong controller và render lại view
                .andExpect(view().name("auth/register"))
                .andExpect(model().attributeExists("registerError"))
                .andExpect(content().string(containsString("Username đã tồn tại")));
    }

    @Test
    void whenRegisterServiceThrowsUnexpectedException_thenStatus500() throws Exception {
        // Chỉ stub khi parameters đúng
        doThrow(new RuntimeException("unexpected"))
                .when(authService)
                .register(eq("err"), eq("e@e.com"), eq("err"));
        mockMvc.perform(post("/auth/register")
                        .param("username", "err")
                        .param("email", "e@e.com")
                        .param("password", "err"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Lỗi hệ thống: unexpected"));
    }
}
