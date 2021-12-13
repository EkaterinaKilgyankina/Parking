package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.domain.RoleEntity;
import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAll();
    }


    @GetMapping("/{email}")
    public UserEntity getUser(@PathVariable String email) {
        UserEntity user = userService.getUser(email);
      /*  if(user == null) {
            throw new UsernameNotFoundException(email);
        }*/
        return user;
    }

    @PostMapping("/{userId}")
    @RolesAllowed("{role_admin}")
    public ResponseEntity changeUserRole (@RequestBody RoleEntity role,
                                 @PathVariable Long userId) {
        return ResponseEntity.ok(userService.changeUserRole(role, userId));
    }

}

