package com.epamtraining.parking.contoller;

import com.epamtraining.parking.entity.RoleEntity;
import com.epamtraining.parking.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping
    public List<RoleEntity> getAllRoles() {
        return roleService.getAll();
    }
}
