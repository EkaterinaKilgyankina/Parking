package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> getAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        if(roles == null) {
            throw new RuntimeException("Nothing to show");
        }

        return roleRepository.findAll();
    }
}
