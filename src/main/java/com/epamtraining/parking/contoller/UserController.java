package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;


    @GetMapping
    @RolesAllowed("{role_admin}")
    public List<UserEntity> getAll() {

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

    @PostMapping("/edit/")
    @RolesAllowed("{role_admin}")
    public ResponseEntity changeUserRole (@RequestBody ChangeRoleRequest role) {
        return ResponseEntity.ok(userService.changeUserRole(role));
    }

}

