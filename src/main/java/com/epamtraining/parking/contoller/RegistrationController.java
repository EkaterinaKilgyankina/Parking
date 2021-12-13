package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserServiceImpl userService;

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    public UserEntity registerUserAccount(@RequestBody UserEntity user) {
        UserEntity registered = userService.registerNewUserAccount(user);
        return registered;
    }
}
