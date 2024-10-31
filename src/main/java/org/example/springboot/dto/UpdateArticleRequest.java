package org.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Arguments 가 없는 생성자 자동 생성
@NoArgsConstructor
// 모든 필드를 Arguments 로 하는 생성자 자동 생성
@AllArgsConstructor
// Getter 와 Setter 메서드 자동 생성
@Getter
@Setter
// 업데이트에서 사용
public class UpdateArticleRequest {
    // 아래 두개 정보 수정
    private String title;
    private String content;
}
