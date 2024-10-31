package org.example.springboot.service;

import org.example.springboot.domain.User;
import org.example.springboot.dto.AddUserRequest;
import org.example.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


// RequiredArgsConstructor Annotation 으로 의존성 주입을 위한 생성자 자동 생성
@RequiredArgsConstructor
// Service Annotation 지정
@Service
public class UserService {
    // 의존성 주입 필요(위에서 해줌) 리포지토리 객체 생성
    private final UserRepository userRepository;
    // 의존성 주입 필요(위에서 해줌) 비밀번호 인코딩 객체 생성
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 유저 저장하기
    public Long save(AddUserRequest req) {
        // email 은 그대로 AddUserRequest 의 email 을 넣고 password 만 bCryptPasswordEncoder.encode(req.getPassword()) 해서 저장
        User newUser = User.builder().email(req.getEmail()).password(bCryptPasswordEncoder.encode(req.getPassword())).build();

        // 저장한 newUser 를 저장(Insert)
        return userRepository.save(newUser).getId();
    }
}
