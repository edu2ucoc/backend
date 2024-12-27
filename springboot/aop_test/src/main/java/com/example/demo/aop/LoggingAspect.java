package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.demo.TestAopApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * @Aspect 적용 -> 기존 코드를 그대로 둔다. AOP 전용 클레스 구성 -> 대상을 관찰하게 등록
 * AOP 서비스가 내부적으로 프록시하여 자동관리
 * - 관심사 => 로그(깅)
 * @Component 
 * 스프링에 컴포넌트 등록, 스프링은 이 클레스는 빈으로 관리
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {
	// 특정 메소드가 실행되기전 로깅
	@Before("execution(* com.example.demo.service.*.*(..))")
	public void logBeforeMethod( JoinPoint joinPoint) {
		log.info("method " + joinPoint.getSignature() + " 실행되기전 로그");
	}	

	// 특정 메소드가 실행된 후 로깅
	@After("execution(* com.example.demo.service.*.*(..))")
	public void logAfterMethod( JoinPoint joinPoint) {
		log.info("method " + joinPoint.getSignature() + " 실행된후 로그");
	}	

	// 특정 메소드가 정상적으로 반환된 후 로깅
	@AfterReturning(value="execution(* com.example.demo.service.*.*(..))", returning="result")
	public void logAfterReturningMethod( JoinPoint joinPoint, Object result) {
		log.info("method " + joinPoint.getSignature() + " 실행된후 정상반환 " + result);
	}	

	// 특정 메소드가 예외 상황을 발생한 후 로깅
	@AfterThrowing(value="execution(* com.example.demo.service.*.*(..))", throwing="ex")
	public void logAfterThrowingMethod( JoinPoint joinPoint, Exception ex) {
		log.error("method " + joinPoint.getSignature() + " 실행될때 오류던지기 " + ex.getMessage());
	}
}
















