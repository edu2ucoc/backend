package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SalesGroupBy;
import com.example.demo.service.SalesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


// 향후 react, vue에서 접근시 crossdomain 문제 해결하기 위해 임시 처리
// 용도 : ajax의 보안이슈 (동일 도메인에서만 작동한다)
// react <-> springboot 연동시 표기!!
@CrossOrigin()
@RequestMapping("/api")
@RestController
public class ApiController {
	// 1. 서비스 DI
	@Autowired
	private SalesService salesService;

	// 2. sales 관련 집계 데이터를 응답하는 API를 제공하는 라우터,메소드구성 
	//    ~/api/sales, GET방식, swagger를 이용한 테스트
	// TODO #PORC2
	@GetMapping("/sales") // ~/api/sales
	// swagger를 위한 문서화 내용 -> 해당 API에 대한 설명 표시
	@Tag(name="세일 데이터 집계", description = "세일 데이터 집계")
	@Operation(summary = "카데고리 개수에 맞춰 집계한 평균 판매가, 평균개수 조회", 
			   description = "카데고리 개수에 맞춰 집계한 평균 판매가, 평균개수 조회")
	public List<SalesGroupBy> sales() {
		// TODO #PORC7 쿼리 결과를 JSON 형태로 응답
		return salesService.getSalesGroupBy();
	}
}















