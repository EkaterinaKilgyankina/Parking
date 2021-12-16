package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/")
public class UserController {
    private UserService userService;

    @PostMapping("/edit/")
    public ResponseEntity changeUserRole (@RequestBody ChangeRoleRequest role) {
        return ResponseEntity.ok(userService.changeUserRole(role));
    }

    // TODO probably we don't need this
    @GetMapping
    public List<UserEntity> getAll() {

        return userService.getAll();
    }
}