package org.example.springboot.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

// 엔티티 객체 지정 Annotation
@Entity
// Getter / Setter 메서드 자동 지정
@Getter
@Setter
// 테이블 Annotation 지정(이름 지정)
@Table(name="users")
// Arguments 가 없는 생성자 자동 생성
@NoArgsConstructor
public class User implements UserDetails {
    // 속성 이름 지정, null 될 수 없음
    @Column(name = "id", nullable = false)
    // DB 따라 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Entity 객체의 PK 지정
    @Id
    private Long id;

    // 속성 이름 지정. null 이 될 수 없고, 중복도 안됨
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // 속성 이름 지정
    @Column(name = "password")
    private String password;

    // 속성 이름 지정
    @Column(name = "create_at")
    private LocalDateTime createAt;

    // 속성 이름 지정
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    // 빌더 패턴 사용
    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 권한 반환(UserDetails 인터페이스 구현)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        // 비밀번호 부여(UserDetails 인터페이스 구현)
        return password;
    }

    @Override
    public String getUsername() {
        // UserRepository 에서 id 로 설정한 email 을 반환(UserDetails 인터페이스 구현)
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았는지를 설정하는 메서드. 만료되지 않았으면 true(UserDetails 인터페이스 구현)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨있지 않은지를 설정하는 메서드. 잠겨있지 않았으면 true(UserDetails 인터페이스 구현)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // password 가 만료되지 않았는지 설정하는 메서드. 만료되지 않았으면 true(UserDetails 인터페이스 구현)
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화되어있는지 설정하는 메서드. 활성화 되어있으면 true(UserDetails 인터페이스 구현)
        return true;
    }
}