package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("could not found user by email:" + user.getEmail());
        }
        return new MyUserDetail(user);
    }

}
