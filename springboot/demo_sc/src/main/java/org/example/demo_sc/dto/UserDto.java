package org.example.demo_sc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * - User 엔티티와 데이터 교환
 *  - 디비용도, 통신용도(로그인, 회원가입등 전달데이터 받는구조)
 */
@Getter
@Setter
@ToString
public class UserDto {
    private String email;
    private String password;
}
