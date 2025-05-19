package org.example.recipes.controller;

import org.example.recipes.login.AuthService;
import org.example.recipes.login.AuthService.LoginResult;
import org.example.recipes.login.Login;
import org.example.recipes.login.Register;
import org.example.recipes.exception.EmailExistsException;
import org.example.recipes.exception.UsernameExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "registered", required = false) String registered,
                                Model model) {
        model.addAttribute("loginForm", new Login());
        if (registered != null) {
            model.addAttribute("loginMessage", "Đăng ký thành công, vui lòng đăng nhập.");
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerForm", new Register());
        return "auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerForm") Register form,
                                 Model model) {
        try {
            authService.register(form.getUsername(), form.getEmail(), form.getPassword());
            // nếu thành công thì redirect kèm flag để show message bên login page
            return "redirect:/auth/login?registered";
        } catch (UsernameExistsException e) {
            model.addAttribute("registerError", "Username đã tồn tại");
            return "auth/register";
        } catch (EmailExistsException e) {
            model.addAttribute("registerError", "Email đã tồn tại");
            return "auth/register";
        }
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("loginForm") Login form,
                              Model model) {
        LoginResult result = authService.login(form.getUsername(), form.getPassword());
        switch (result) {
            case USER_NOT_FOUND:
                model.addAttribute("loginError", "Username hoặc Email không tồn tại");
                return "auth/login";
            case INVALID_PASSWORD:
                model.addAttribute("loginError", "Sai mật khẩu");
                return "auth/login";
            case SUCCESS:
            default:
                // đăng nhập thành công
                return "redirect:home/recipes";
        }
    }
}
