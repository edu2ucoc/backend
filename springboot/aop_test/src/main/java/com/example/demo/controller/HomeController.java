package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.aop.AroundLoggingAspect;
import com.example.demo.service.TargetService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 기본 문자열 hello world  출력하는 홈페이지 준비 
 */
// 롬복의 로그 처리 -> log.xxx() 이후로는 System.out.println() x -> 서버 부하 발동
@Slf4j
@Controller
public class HomeController {
	@Autowired
	private TargetService targetService;	// aop가 체크하는 타겟 메소드를 가진 서비스 
	
	/**
	 * 클라이언트 정보 수집 -> 요청(Request 이름이 가미될것)
	 * 	- User-Agent : 고객식별 정보, 어떤 OS, 매체(PC,모바일,모바일의특정앱), 브라우저정보, ....
	 *   	- 매체별(고객별,타겟별)로 다른 서비스 제공 가능!! -> 타켓팅
	 *   	- 반응형웹 처리시 활용 (User-Agent, 해상도 활용)
	 * @return
	 */
	@GetMapping("/")
	@ResponseBody
	public String home(
			// 0. 요청 객체 파라미터
			HttpServletRequest request,
			// 1. 별도의 헤더정보를 선정해서 파라미터로 추출 가능하다 !!
			//    인증키, 시크릿키등 별도의 중요 정보들도 헤더에 짃접 추출 가능!!
			@RequestHeader(value="User-Agent") String userAgent) {

		// 2. userAgent
		//    상세적인 브라우저, os, 모바일/PC등 정보등은 내용 해석해서 파악 -> 접속 매체 분석 가능하다
		log.info("User-Agent " + userAgent);
		log.info("User-Agent " + request.getHeader("User-Agent") );

		// 2. IP 정보 추출 -> 실시간 모니터링 -> 단시간에 특정 IP로 많은 요청이 온다면 -> DDOS 정황으로 본다
		String ipAddress = request.getRemoteAddr();
		// IP 트레킹 가능 (자기 자신은 0.0.0.0.. )

		// proxy -> IP를 우회화여 접근할때 사용 (1회성, 여러번 거쳐서 접속 가능..)
		// proxy(엘리트급!!(최상)은 체크않됨)를 통해서 우회해서 진입 -> 나를 특정하지 못하게 접근하기 위해 우회하는 경우
		String forwardedFor = request.getHeader("X-Forwarded-For");
		if( forwardedFor != null) {
			// 아래 코드 전까지 ipAddress == 우회n
			// [ 실사용자IP, 우회1, 우회2, 우회3,.... 우회n]
			log.warn("Reverse Proxy " + forwardedFor);
			ipAddress = forwardedFor.split(",")[0]; // 여러개값중 첫번째값을 취한다 => 실사용자IP
		}

		String host = request.getRemoteHost();
		String user = request.getRemoteUser();
		int port 	= request.getRemotePort();
		
		log.info("IpAddress " + ipAddress);
		log.info("Host " + host);
		log.info("User " + user);
		log.info("Port " + port);
		
		// 3. 요청 URL 정보
		String url = request.getRequestURL().toString();
		log.info("RequestUrl " + url);
		
		// 엔드포인트
		// 서비스를 이용하여 특정 비즈니스 로직을 수행한다 -> aop가 감지해서 로깅처리하겠다
		
		// 특정 비즈니스 로직
		// targetService.runTask() 이 메소드(가정, 특정 비즈니스 로직)에 aop 적용
		targetService.runTask(); // 타겟이 작동할려고 한다 => aop 작동 => 로그출력(=>수집업그레이드)
		
		return "Helloworld";
	}
	
	@GetMapping("/ex")
	@ResponseBody
	public String ex( HttpServletRequest request) {		
		// 특정 비즈니스 로직
		targetService.collectClient( request );		
		return "Helloworld";
	}
}









