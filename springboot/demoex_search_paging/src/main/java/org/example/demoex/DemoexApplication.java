package org.example.demoex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoexApplication {
    /**
     * 스프링부트라는 백엔드 서버 서비스가 구동되면,
     * 관리자(서비스)는 main함수를 찾아서 호출함으로써 서버가 가동된다!!
     * 엔트리 포인트
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoexApplication.class, args);
    }

}
