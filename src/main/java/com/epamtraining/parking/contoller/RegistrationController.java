package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/registration/")
public class RegistrationController {
    private UserServiceImpl userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    public UserEntity registerUserAccount(@RequestBody UserEntity user) {
        return userService.registerNewUserAccount(user);
    }

    @PostMapping("/admin")
    public UserEntity registerAdminAccount(@RequestBody UserEntity user) {
        return userService.registerAdminAccount(user);
    }
}
