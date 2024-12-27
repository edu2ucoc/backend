package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.SalesGroupBy;
import com.example.demo.dto.SalesGroupByInterface;
import com.example.demo.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer>{
	// TODO #PORC4 JPA를 통해서 디비측에 SQL을 수행하여 결과 획득
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





