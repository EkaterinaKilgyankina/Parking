package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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



    @GetMapping("/{email}")
    public UserEntity getUser(@PathVariable String email) {
        UserEntity user = userService.getUser(email);
      /*  if(user == null) {
            throw new UsernameNotFoundException(email);
        }*/
        return user;
    }

}

