package com.example.demo;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 스프링부트와 MyBatis 연동시 필요한 요소를 빈으로 등록
 */
@Configuration
public class MyBatisConfig {
	/**
	 * mybatis-config.xml 세팅 주의(오타 주의)
	 *  - Mybatis와 데이터베이스간 세션 관리
	 *  - 필요시 SqlSession 인스턴 생성시 관여
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource); // 데이터 소스 설정
		// 커스텀으로 구성한 설정 파일을 연결
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return sqlSessionFactoryBean.getObject();
	} 
	/**
	 * SqlSessionTemplate 빈 생성
	 * 	- MyBatis의 SqlSession 구현
	 *  - 스프링 트랜젝션 설정 통합 용이하게 제공
	 *  - 안정적인 작동을 위한 스레드 처리
	 *  - SQL 처리(실행), 트랜젝션 관리
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}










