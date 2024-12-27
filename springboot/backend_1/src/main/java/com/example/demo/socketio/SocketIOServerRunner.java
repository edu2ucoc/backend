package com.example.demo.socketio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;

/**
 * TODO IO-STEP #006  소켓IO 서버 가동을 위한 실행 클레스
 *  - 이벤트 등록후 서버 가동하는것이 순서, 편의상 작성을 먼저 진행함
 *  
 * @Component 
 * 	- 해당 클레스를 자바 빈으로 등록하라고 알려주는 기능
 *  - 스프링부트 가동시 ApplicationContext라는 의존성 주입 컨테이너에 해당 빈객체를 등록처리한다
 */
@Component
public class SocketIOServerRunner implements CommandLineRunner{
	// DI 생성자 방식을 lombok 없이 구성한 코드
	private final SocketIOServer server;
	public SocketIOServerRunner(SocketIOServer server) {
		super();
		this.server = server;
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub	
		// 소켓 IO 서버 가동 -> 실시간 통신이 가능하게 처리된다!!
		server.start();
		System.out.println("소켓 IO 서버 가동!!");
	}	
}











