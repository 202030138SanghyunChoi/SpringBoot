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

@Entity
@Getter
@Setter
@Table(name="users")
@NoArgsConstructor
public class User implements UserDetails {
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        // 비밀번호 부여
        return password;
    }

    @Override
    public String getUsername() {
        // 아이디 부여(고유값으로 설정해놓은 필드)
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 만료되지 않았니??? 물어봄
        // ㅇㅇ 않았음 == true
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 잠겨 있지 않았니? 물어봄
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // password 만료 기간이 되지 않았니?
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        // 계정활성화 되있는지
        return true;
    }
}