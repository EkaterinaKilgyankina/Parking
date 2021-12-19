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

    @PostMapping("/edit/{userId}")
    public ResponseEntity changeUserRole (@RequestBody ChangeRoleRequest role, @PathVariable Long userId) {
        return ResponseEntity.ok(userService.changeUserRole(userId, role));
    }

    // TODO probably we don't need this
    @GetMapping
    public List<UserEntity> getAll() {

        return userService.getAll();
    }
}