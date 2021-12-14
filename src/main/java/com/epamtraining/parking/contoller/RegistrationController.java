package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return userService.registerNewUserAccount(user);
    }

    @PostMapping("/admin")
    public UserEntity registerAdminAccount(@RequestBody UserEntity user) {
        return userService.registerAdminAccount(user);
    }
}
