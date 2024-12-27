package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SalesGroupBy;
import com.example.demo.dto.SalesGroupByInterface;
import com.example.demo.repository.SalesRepository;

@Service
public class SalesService {
	@Autowired
	private SalesRepository salesRepository;
	
	// TODO #PORC3
	public List<SalesGroupBy> getSalesGroupBy() {
		List<SalesGroupByInterface> sales = salesRepository.findSalesGroupByData();
		System.out.println("데이터의 총 개수" + sales.size() );
		// 이상 없는 분은 sales에서 데이터를 추출하여 SalesGroupBy 객체에 담아서
		// 객체에 담은후, 값 확인 => toString()
		// List<SalesGroupBy> res 에 담아서 size() 출력
		// TODO #PORC6 쿼리 결과를 DTO로 데이터 전환 처리
		List<SalesGroupBy> res = new ArrayList<SalesGroupBy>();
		// 인터페이스 객체 추출 -> DTO 세팅
		for(SalesGroupByInterface sale : sales) {
			// DTO 객체를 하나씩 생성해서 데이터를 세팅(데이터 교환)
			SalesGroupBy data = SalesGroupBy.builder()
					.category(sale.getCategory())
					.cnt(sale.getCnt())
					.price(sale.getPrice())
					.amount(sale.getAmount())
					.build();
			//log.info("데이터 확인 " + data.toString());
			res.add(data);
		}
		//log.info("데이터 확인 " + res.size());
		return res;
	}
}











