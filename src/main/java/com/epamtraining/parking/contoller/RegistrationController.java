package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/registration/")
public class RegistrationController {
    private UserServiceImpl userService;

    @PostMapping
    //TODO разделение domain  и controller ? userEntity  vs userRequest
    public ResponseEntity registerUserAccount(@RequestBody @Valid UserRequest user) {
        return ResponseEntity.ok(userService.registerNewUserAccount(user));
    }

    // TODO probably should be hidden somehow in real world
    @PostMapping("/admin")
    public ResponseEntity registerAdminAccount(@RequestBody @Valid UserRequest user) {
        return ResponseEntity.ok(userService.registerAdminAccount(user));
    }

    // TODO probably will be useful when start work on UI
    @GetMapping
    public String showRegistrationForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "registration";
    }
}
