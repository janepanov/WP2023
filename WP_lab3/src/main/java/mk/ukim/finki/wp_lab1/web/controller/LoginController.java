package mk.ukim.finki.wp_lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp_lab1.model.User;
import mk.ukim.finki.wp_lab1.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        Optional<User> user = authService.login(request.getParameter("username"), request.getParameter("password"));

        if(user.isPresent())
        {
            request.getSession().setAttribute("user", user);
            return "redirect:/movies";
        }

        return "redirect:/login";
    }
}