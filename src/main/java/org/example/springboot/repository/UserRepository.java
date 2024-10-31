package org.example.springboot.repository;

import org.example.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository 확장하므로써 CRUD 기본 탑재(Entity 객체 이름과 PK 필드의 타입)
public interface UserRepository extends JpaRepository<User, Long> {
    // id 값으로 사용할 필드를 findBy 해놓기
    Optional<User> findByEmail(String email);
}
