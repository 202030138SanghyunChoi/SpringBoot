package org.example.springboot.service;

import org.example.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Service Annotation 지정
@Service
// RequiredArgsConstructor Annotation 으로 의존성 주입을 위한 생성자 자동 생성
@RequiredArgsConstructor
// UserDetailService 인터페이스 구현함으로써 사용자 아이디(여기선 email 필드로 설정)로 찾은 사용자의 정보를 UserDetails 객체로 만들어 Provider 에게 전달한다.
public class UserDetailService implements UserDetailsService {

    // 의존성 주입 필요(위에서 해줌) 리포지토리 객체 생성
    private final UserRepository userRepository;

    // UserDetailService 인터페이스 구현
    // email 로 id를 설정했기 때문에 email 로 findBy
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}