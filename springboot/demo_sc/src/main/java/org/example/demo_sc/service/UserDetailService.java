package org.example.demo_sc.service;

import lombok.Setter;
import org.example.demo_sc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetail 는 스프링시큐리티에서 유저의 인증정보등등 관리하는 객체
 *  - 인증, 권한등에 대한 비즈니스 로직 처리
 *  - 해당 유저가 회원인지 체크
 *  - 로그인에 관련된 쿼리 요구가 시도될수 있다
 */
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // 사용자의 username을 이용하여 회원 조회
    // username -> 엔티티 -> email 컬럼으로 커스텀 구성했음 (getUserName())
    @Override
    public UserDetails loadUserByUsername(String username_email) throws UsernameNotFoundException {
        // 레포지토리를 통해서 쿼리 수행 -> DI
        // userRepository를 이용하여 쿼리 수행, 재료 username_email -> 유저획득
        // 메소드 확장 제공, findBy컬럼명 -> 확장됨
        // 이메일로 조회 => 비번은 내부적으로 사용하여 조회 => 회원 여부 판단
        System.out.println("사용자 정보 전달 : " + username_email);
        return this.userRepository.findByEmail(username_email)
                .orElseThrow( ()->new IllegalArgumentException(username_email));
    }
}
