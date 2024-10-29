package org.example.springboot.service;

import org.example.springboot.domain.User;
import org.example.springboot.dto.AddUserRequest;
import org.example.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest req) {
        User newUser = User.builder().email(req.getEmail()).password(bCryptPasswordEncoder.encode(req.getPassword())).build();

        return userRepository.save(newUser).getId();
    }
}
