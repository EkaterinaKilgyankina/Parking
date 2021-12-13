//package com.epamtraining.parking.services;
//
//import com.epamtraining.parking.domain.entity.UserEntity;
//import com.epamtraining.parking.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class MyUserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username)
//                .map(u -> User.builder()
//                        .username(u.getEmail())
//                        .password(u.getPassword())
//                        .authorities(getAuthorities(u))
//                        .build())
//                .orElse(User.builder()
//                        .username(" ")
//                        .password(" ")
//                        .authorities(new SimpleGrantedAuthority("none"))
//                        .build());
//
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity u) {
//        return u.getRoles().stream()
//                .map(e -> e.getName())
//                .map(e -> new SimpleGrantedAuthority(e))
//                .collect(Collectors.toList());
//    }
//}
