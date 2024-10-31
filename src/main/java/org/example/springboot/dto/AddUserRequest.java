package org.example.springboot.dto;

import lombok.Getter;
import lombok.Setter;

// Getter / Setter 자동 생성
@Getter
@Setter
public class AddUserRequest {
    // email 이랑 password 정보(생성은 UserService 에서)
    private String email;
    private String password;
}
