/**
 * SQL 수행결과(집계 쿼리)를 받아줄 DTO
 * 수행 결과를 1대 1로 받아줄 Entity는 존재하지 않음 -> DTO로 대체 처리
 * 만약 DTO 데이터가 입력되지 않으면, 동일 컬럼명의 인터페이스를 구축(Getter만 구현)하여 받아준다
 */
package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 쿼리 결과를 받는 객체 준비
 * 	- DTO 컨셉
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SalesGroupBy {
	private String category;
	private int cnt;
	private float price;
	private float amount;
	@Builder
	public SalesGroupBy(String category, int cnt, float price, float amount) {
		super();
		this.category = category;
		this.cnt = cnt;
		this.price = price;
		this.amount = amount;
	}	
}
