package org.example.recipes.login;

import org.example.recipes.Exception.UsernameExistException;
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

    // 1) Hiển thị trang login
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new Login());
        return "auth/login";
    }

    // 2) Hiển thị trang register
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerForm", new Register());
        return "auth/register";
    }

    // 3) Xử lý login
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("loginForm") Login form,
                              Model model) {
        boolean ok = authService.login(form.getUsername(), form.getPassword());
        if (!ok) {
            model.addAttribute("loginError", "Sai username hoặc password");
            return "auth/login";
        }
        // Sau khi login thành công, thường redirect về trang chủ
        return "redirect:/auth/register";
    }

    // 4) Xử lý register
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerForm") Register form,
                                 Model model) {
        try {
            authService.register(form.getUsername(), form.getEmail(), form.getPassword());
            // đăng ký xong thì chuyển về login, kèm flag thông báo
            return "redirect:/auth/login?registered";
        } catch (UsernameExistException e) {
            model.addAttribute("registerError", "Username đã tồn tại");
            return "auth/register";
        }
    }
}
