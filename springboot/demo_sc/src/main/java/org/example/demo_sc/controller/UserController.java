package org.example.demo_sc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo_sc.dto.UserDto;
import org.example.demo_sc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * - 인증없이 접근가능함 : 로그인, 회원가입
 * - 인증후에 접근가능함 : 로그아웃 (필요시 컨트롤러 분리 가능함)
 * - 시나리오
 *  - 회원가입 -> 로그인 -> 홈페이지 자동이동 -> 로그아웃 클릭 -> 로그인
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // 로그인
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 회원가입
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("/signup_process")
    public String signup_process(UserDto userDto) {
        // 1. 전달 데이터 확인
        System.out.println("회원 가입용 데이터 전달 : "+userDto.toString());
        // 2. 서비스를 이용하여 회원가입 처리 UserService 처리 -> DI
        userService.create( userDto );
        // 3. 회원가입 성공후 로그인 이동
        return "redirect:/login"; // or "redirect:/" <= 홈으로 포워딩 -> 보안걸려서 -> 로그인이동함
    }

    // 로그아웃 작성
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 1. 인증 해제(로그아웃), (요청, 응답, 현재 사용자의 인증정보를 전달)
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        // 2. 최종 로그인으로 이동
        return "redirect:/login";
    }

}
