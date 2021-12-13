package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}
