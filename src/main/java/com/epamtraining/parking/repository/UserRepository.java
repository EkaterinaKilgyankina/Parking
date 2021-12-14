package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity u WHERE u.email = :email")
    public UserEntity getUserEntityByEmail(@Param("email") String email);

}