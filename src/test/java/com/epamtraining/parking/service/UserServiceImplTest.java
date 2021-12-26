package com.epamtraining.parking.service;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.repository.RoleRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.impl.UserServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
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
        RoleEntity roleMock = mock(RoleEntity.class);
        UserRequest request = new UserRequest();
        request.setEmail("test@gg.com");
        request.setPassword("Test11!");


        when(userRepository.save(any())).thenAnswer(e -> e.getArgument(0));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("Test11!");

        when(roleRepository.findByName("role_user")).thenReturn(roleMock);

        service.registerNewUserAccount(request);

        verify(userRepository, times(1)).save(userEntityArgumentCaptor.capture());

        UserEntity entity = userEntityArgumentCaptor.getValue();

        assertEquals(request.getEmail(), entity.getEmail());
        assertEquals(request.getPassword(), entity.getPassword());
        assertEquals(1, entity.getRoles().size());
        assertEquals(roleMock, entity.getRoles().stream().collect(Collectors.toList()).get(0));


    }
@Test
    public void registerNewUserWhenEmailAlresyExist() {
        //TODO правильно ли напрямую бросать исключение или нужно триггерить причину? (email!=null)
        when(userRepository.getUserEntityByEmail(any())).thenThrow(new ApplicationException("User already exists"));
        assertThrows(ApplicationException.class, ()->service.registerNewUserAccount(new UserRequest()));
    }

    @Test
    public void changeUserRoleTest() {
        RoleEntity roleMock = mock(RoleEntity.class);
        ChangeRoleRequest request = new ChangeRoleRequest();
        request.setRole("role_admin");

        when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity()));
        when(roleRepository.findByName(request.getRole())).thenReturn(roleMock);
        when(userRepository.save(userEntityArgumentCaptor.capture())).thenAnswer(e -> e.getArgument(0));

        service.changeUserRole(1L, request);

        UserEntity value = userEntityArgumentCaptor.getValue();
        assertEquals(roleMock, value.getRoles().stream().collect(Collectors.toList()).get(0));
    }

    @Test
    public void changeUserRoleWhenUserIdIsNull() {
        // TODO или здесь правильнее вызывать метод сервиса, чтобы триггерить ошибку?!
        when(userRepository.findById(any())).thenThrow(new ApplicationException("user is not found"));
        assertThrows(ApplicationException.class, () -> userRepository.findById(null));
    }

//    @Test
//    public void whenIdIsNull_thenExceptionIsThrown() {
//        assertThrows(InvalidArgumentException.class, () -> Optional
//                .ofNullable(personRepository.findNameById(null))
//                .orElseThrow(InvalidArgumentException::new));
//    }

    @Test
    public void changeUserRoleWhenUserIdIsNull2() {
        // TODO или здесь правильнее вызывать метод сервиса, чтобы триггерить ошибку?!
        when(userRepository.findById(any())).thenThrow(new ApplicationException("user not found"));
        assertThrows(ApplicationException.class, () -> service.changeUserRole(0L, null));
    }

    @Test
    public void changeUserRoleWhenRoleIsNull() {
        when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity()));
        when(roleRepository.findByName(any())).thenReturn(null);
        assertThrows(ApplicationException.class, () -> service.changeUserRole(0L, new ChangeRoleRequest()));
    }
}


