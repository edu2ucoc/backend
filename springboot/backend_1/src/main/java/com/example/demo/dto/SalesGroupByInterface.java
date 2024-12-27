/**
 * 만약 DTO 데이터가 입력되지 않으면, 
 * get+동일컬럼명() 구성하여
 * 인터페이스를 구축(Getter만 구현)하여 받아준다
 */
package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// TODO #PORC5 쿼리 결과를 해당 객체를 생성하여 담아둔다
public interface SalesGroupByInterface {
	// 결과셋의 이름을 기반으로 get+이름(첫글자대문자) 인터페이스 구성, 메소드 선언으로
	String getCategory();
	int getCnt();
	float getPrice();
	float getAmount();
}
