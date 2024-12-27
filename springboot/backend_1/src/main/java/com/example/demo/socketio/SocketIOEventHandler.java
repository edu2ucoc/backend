package com.example.demo.socketio;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * TODO IO-STEP #007 클라이언트 서버간 소켓 통신을 위한 모든 작업
 * - 클라이언트가 보낸 메세지 => 이벤트 등록하여 처리
 * - 클라이언트에게 보낼 메세지 구성
 * 
 * 정상 작성 여부 체크
 * curl
 * "https://openapi.naver.com/v1/search/news.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim"
 * \
 * -H "X-Naver-Client-Id: 본인키" \
 * -H "X-Naver-Client-Secret: 본인시크릿키" -v
 * 
 */

// 서버측에서 실제로 작업하는 내용, 네이버 검색 응답 등등 각종 작업 세팅
// GPT 연동하는 지점도 여기 처리
@Component
public class SocketIOEventHandler {
	// 통신용 모듈(도구) (네이버 | GPT 등과 외부서버와 통신하는 모듈)
	@Autowired
	private UtilExternalNet utilExternalNet;

	// 생성자 방식 DI
	private final SocketIOServer server;
	public SocketIOEventHandler(SocketIOServer server) {
		super();
		this.server = server;

		// 이벤트 설정
		// server 객체를 통해서 메세지 수신, 메세지 전송
		// 1. 클라이언트가 접속하였다(연결되었다)
		server.addConnectListener(socket -> {
			// socket : 접속한 클라이언트
			System.out.println("클라이언트가 접속하였다 : " + socket.getSessionId().toString());
			// 클라이언트로 메세지 전송
			// 메세지 형태는, 문자열 통상( 구조는 json 혹은 일반 형태 가능함 )
			// "svr_init_msg" : 임의로 설정한 이벤트명
			// 서버 -> 클라이언트 메세지 전송
			socket.sendEvent("svr_init_msg", "어떤 뉴스가 궁금하신가요?"); // 일반 텍스트 형태로 전송
		});
		// 2. 클라이언트가 접속이 끝났다(연결 종료 되었다)
		server.addDisconnectListener(socket -> {
			// socket : 접속 해제한 클라이언트
			System.out.println("클라이언트 접속해제 : " + socket.getSessionId().toString());
		});
		// 3. 클라이언트가 보낸 커스텀메세지 처리 -> 뉴스 검색, 확장 가능
		//    이 메소드를 여러개 구성, 이벤트명을 달리구성, 내부처리(네이버검색, gpt 처리, 기타 작업등등 확장)
		server.addEventListener("cvr_news", String.class, new DataListener<String>() {
			@Override
			public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
				// TODO Auto-generated method stub
				// System.out.println("수신된 메세지 => 뉴스검색결과 : " + naverOpenApiForNews(data) );
				client.sendEvent("svr_news", naverOpenApiForNews(data));
				// 클라이언트는 메세지를 받아서 제목만 추출하여 콘솔에 출력
			}
		});
	}

	// 환경변수값을 읽어와서 변수값에 세팅
	@Value("${naver.client.id}")
	private String client_id;
	@Value("${naver.client.secret}")
	private String client_secret;

	// 실제 통신 처리
	private String naverOpenApiForNews(String keyword) {
		String clientId = client_id; // 애플리케이션 클라이언트 아이디값"
		String clientSecret = client_secret; // 애플리케이션 클라이언트 시크릿값"

		// 키워드 url인코딩 처리 수행
		keyword = URLEncoder.encode(keyword);
		// String apiURL =
		// "https://openapi.naver.com/v1/search/news.json?display=10&start=1&sort=sim&query="
		// + keyword;
		String apiURL = "https://openapi.naver.com/v1/search/news.json?display=10&start=1&sort=date&query=" + keyword;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = utilExternalNet.get(apiURL, requestHeaders);
		// System.out.println(responseBody);
		return responseBody;
	}
}
