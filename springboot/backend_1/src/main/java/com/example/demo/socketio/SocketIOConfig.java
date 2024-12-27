package com.example.demo.socketio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.corundumstudio.socketio.Configuration;

import com.corundumstudio.socketio.SocketIOServer;

/**
 * TODO IO-STEP #005 소켓IO 서버 구성을 위한 환경설정
 *  - hostname, port 설정 외에는 수정 X
 * @Configuration
 * 	- Bean을 수동으로 등록할때 절차
 * 		- class 위에  @Configuration 추가
 * 		- class 내부에 특정 메소드(특정 객체를 반환)위에 @Bean 추가
 */
@org.springframework.context.annotation.Configuration
public class SocketIOConfig {
	// application.properties에서 정보를 획득하여 맴버 변수의 초기값으로 세팅
	@Value("${socketio.server.hostname}")
	private String hostname; // 환경변수를 읽어서 맴버 변수이 초기값으로 세팅 @Value("${환경변수}")
	@Value("${socketio.server.port}")
	private int port;

	// 서버측에서 웹소켓 사용할수 있게 빈 등록
	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
		// 1. hostname
		config.setHostname(hostname);
		// 2. port
		config.setPort(port);
		return new SocketIOServer( config );
	}
}











