package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;


    @GetMapping
    @RolesAllowed("role_admin")
    public List<UserEntity> getALl() {

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

}

