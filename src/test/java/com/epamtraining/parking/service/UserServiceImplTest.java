package com.epamtraining.parking.service;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.repository.RoleRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserServiceImpl service;

    @Captor
    ArgumentCaptor<UserEntity> userEntityArgumentCaptor;


    @Test
    public void registerNewUserTest() {
        UserRequest request = new UserRequest();
        request.setEmail("test@gg.com");
        request.setPassword("Test11!");

        when(userRepository.save(any())).thenAnswer(e -> e.getArgument(0));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("111");

        RoleEntity mock = mock(RoleEntity.class);
        when(roleRepository.findByName("role_user")).thenReturn(mock);

        UserEntity result = service.registerNewUserAccount(request);

        verify(userRepository, times(1)).save(userEntityArgumentCaptor.capture());

        UserEntity entity = userEntityArgumentCaptor.getValue();
        assertEquals(request.getEmail(), entity.getEmail());
        assertEquals(request.getPassword(), entity.getPassword());
        assertEquals(1, entity.getRoles().size());
        assertEquals(mock, entity.getRoles().stream().collect(Collectors.toList()).get(0));


    }

    @Test
    public void changeUserRoleTest() {

        ChangeRoleRequest request = new ChangeRoleRequest();
        request.setRole("role_admin");

        when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity()));
        RoleEntity mock = mock(RoleEntity.class);

        when(roleRepository.findByName(request.getRole())).thenReturn(mock);
        when(userRepository.save(userEntityArgumentCaptor.capture())).thenAnswer(e -> e.getArgument(0));

        service.changeUserRole(1L, request);

        UserEntity value = userEntityArgumentCaptor.getValue();
        assertEquals(mock, value.getRoles().stream().collect(Collectors.toList()).get(0));
    }
}

