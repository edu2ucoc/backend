package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.SalesGroupBy;
import com.example.demo.dto.SalesGroupByInterface;
import com.example.demo.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer>{
	// TODO #PORC4 JPA를 통해서 디비측에 SQL을 수행하여 결과 획득
	// JPA로 구성하기 힘든 쿼리문은 직접 SQL 작성하여 구동 -> @Query
	// 결과셋 일치하는 엔티티는 없다, 재료는 sales 엔티티이다
	@Query("select\r\n"
			+ "    cate category,\r\n"
			+ "    count(cate) cnt,\r\n"
			+ "    round(avg(price),2) price,\r\n"
			+ "    round(avg(amount),2) amount\r\n"
			+ "from Sales\r\n"
			+ "group by cate\r\n"
			+ "order by price desc")
	List<SalesGroupByInterface> findSalesGroupByData();
}





