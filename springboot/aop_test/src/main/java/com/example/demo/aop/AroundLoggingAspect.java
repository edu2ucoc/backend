package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Around 적용
 * - 특정 메소드 호출 전후, 결과반환/예외반환까지 한 함수에서 체크
 * - 특정 메소드의 수행시간에 대한 로그 기록(아직 기록전)
 */
@Slf4j
@Aspect
@Component
public class AroundLoggingAspect {
	@Around("execution(* com.example.demo.service.*.*(..))")
	public void logAroundMethod( ProceedingJoinPoint joinPoint) throws Throwable {
		// 메소드 작동 시간 측정하여 로그 기록
		// 1. 메소드가 호출되기전에 시간 측정
		long s = System.currentTimeMillis(); // GMT+9시간
		// 2. 메소드 호출
		Object result = joinPoint.proceed();
		// 3. 메소드가 호출완료될때 시간 측정
		long e = System.currentTimeMillis(); // GMT+9시간
		// 4. 소요시간 = 호출완료-호출되기전		
		log.info("method " + joinPoint.getSignature() + " 실행시간 " + (e-s) + " ms");
		// 목적 : 로그를 통해서 메소드의 수행시갖 체크 => 평균, 병목구간 점검, 눈에 띠는 지연시간 되면 -> 분석, 개선
		// 해당 코드에 세팅하지 않고,관찰을 통해서 수집 => 유지보수!!
	}	
}














