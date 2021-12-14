package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.services.impl.RoleServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private RoleServiceImpl roleService;

    @GetMapping
    public List<RoleEntity> getAllRoles() {
        return roleService.getAll();
    }
}
