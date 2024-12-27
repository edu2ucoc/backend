package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.TargetService;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링 부트 구동 엔트리 포인트 => main() 메소드
 */
@SpringBootApplication
public class TestAopApplication implements CommandLineRunner{
	@Autowired
	private TargetService targetService;
	
	public static void main(String[] args) {
		SpringApplication.run(TestAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// TestAopApplication 이 생성될때 호출된다
		targetService.appStart();
	}	
}












