package org.example.recipes.login;

import org.example.recipes.exception.UsernameExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    // Hiển thị trang login, có thể nhận flag registered để show thông báo
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "registered", required = false) String registered,
                                Model model) {
        model.addAttribute("loginForm", new Login());
        if (registered != null) {
            model.addAttribute("loginMessage", "Đăng ký thành công, vui lòng đăng nhập.");
        }
        return "auth/login";
    }

    // Hiển thị trang đăng ký
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerForm", new Register());
        return "auth/register";
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("loginForm") Login form,
                              Model model) {
        boolean ok = authService.login(form.getUsername(), form.getPassword());
        if (!ok) {
            model.addAttribute("loginError", "Sai username hoặc password");
            return "auth/login";
        }
        // Đăng nhập thành công -> chuyển đến trang chủ
        return "redirect:/";
    }

    // Xử lý đăng ký
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerForm") Register form,
                                 Model model) {
        try {
            authService.register(form.getUsername(), form.getEmail(), form.getPassword());
            return "redirect:/auth/login?registered";
        } catch (UsernameExistsException e) {
            model.addAttribute("registerError", "Username đã tồn tại");
            return "auth/register";
        }
    }
}
