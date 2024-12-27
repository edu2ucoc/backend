package org.example.demo_sc.repository;

import org.example.demo_sc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 회원 관련 SQL 지원
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // findBy필드명 메소드 무한확장 -> 커스텀 검색 기능 추가
    Optional<User> findByEmail(String usernameEmail);
}
