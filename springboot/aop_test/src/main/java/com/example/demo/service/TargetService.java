package com.example.demo.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 현 프로젝트에서 AOP가 모니터링 하면서 메소드 콜이 발생하면
 * 로그를 출력(향후 로깅처리)하는 대상
 */
@Service
public class TargetService {
	// aop가 모니터링하는 타겟 메소드!!
	public String runTask() {
		// 비즈니스 로직 -> 향후 수집하는 내용은 
		// 접속한 클라이언트의 IP, user-Agent, 가능한 개인정보 추출하여 로깅
		try {
			Thread.sleep( 1000*1 ); // 임의로 지연시간 부여(메소드 수행시간)
		}catch( Exception e ) {
			System.out.print(e.getMessage());
		}
		return "runTask call and value return";
	}
	public String appStart() {		
		return "SB Server (Re)Start";
	}
	public String collectClient(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String userAgent = request.getHeader("User-Agent");
		String ipAddress = request.getRemoteAddr();
		String forwardedFor = request.getHeader("X-Forwarded-For");
		if( forwardedFor != null) {
			ipAddress = forwardedFor.split(",")[0]; // 여러개값중 첫번째값을 취한다 => 실사용자IP
		}
		String host = request.getRemoteHost();
		String user = request.getRemoteUser();
		int port = request.getRemotePort();
		String url = request.getRequestURL().toString();
		
		String method = request.getMethod();
		
		// 요청에 대한 로그 메세지 포멧
		return String.format("IP:%s\tHOST:%s\tUSER:%s\tPORT:%d\tURL:%s\tMETHOD:%s\tUSERAGENT:%s", 
				ipAddress, host, user+"", port, url, method, userAgent);
	}
}








