# 스프링부트
    - 사전 지식
        - 웹에 대한 간단한 이해/사용
        - 자바 사용 간단하게라도 이해하면 가능
        - 디비 사용 간단하게라도 이해하면 가능
        - html/css/js 로 화면 구성되는것 간단하게라도 이해하면 가능
    - 프로젝트 간단하게 생성하기        
        - hello world 출력
        - 프로젝트 생성
            - 햄버거 > project > springboot 선택
            - 기본 입력값 유지 > 프로젝트 위치만 조정 (~/backend/springboot)
            - next 클릭
            - springboot dev tool, lombok, spring web 선택(체크)
            - next or create 클릭
            - 프로젝트 생성됨
        - 서비스 생성
            - src/main/java/com.example.demo 밑에서 우클릭 > new > class > HomeController 엔터
            ```
                import org.springframework.stereotype.Controller;
                import org.springframework.web.bind.annotation.GetMapping;
                import org.springframework.web.bind.annotation.ResponseBody;

                // http://localhost:8080 접속
                @Controller
                public class HomeController {
                    @GetMapping("/")
                    @ResponseBody
                    public String home() {
                        return "hello world 스프링부트";
                    }
                }

            ```
        - 실행
        - 브라우저 접속
            - http://localhost:8080

    - 목적 
        - 웹서비스 구현 => 클라이언트에게 서비스 제공
            - 쿠팡, 네이버, 뱅킹, 노션, 주식, 블로그, ... 

# 개요
    - 백엔드 구성
        - 유사 제품 : (*)Springboot, .net, nodejs, django/flask/(*)fastapi, php,...
        - 언어를 중심으로 분류됨
    - 프레임웍 vs 라이브러리 
        - 프레임웍은 해당 제품의 `룰`(구조, 규칙)에 따라 코드가 작성되어야 한다
        - 라이브러리는 내가 작성한 코드에 사용(적용), 나의 룰에 따라 작성된다, 단 사용법은 있음
    - 스프링부트
        - 웹서비스를 쉽고 빠르게 개발하기 위해 자바 언어를 이용하여 만든 프레임웍이다
        - 필요한 대부분의 기능들은 라이브러리로 제공
            - 프로젝트 구성시 쇼핑하듯이 필요한 제품 선택 : 데브툴, 롬복, jpa, 타임리프, h2, web.....
            - 나중에 추가 가능
    - 스프링부트(프레임웍) 구조
        ```
        .
        ├── HELP.md
        ├── build                               : 개발(*.class, 리소스), 배포(*.jar|war) 생성됨
        ├── (*)build.gradle                        : 사용 라이브러리 기술/편집, 빌드 정보(JDK, 이름등)
        ├── gradle
        │   └── wrapper
        │       ├── gradle-wrapper.jar
        │       └── gradle-wrapper.properties
        ├── gradlew                     : 빌드 명령어 기술되어 있음 -> CI/CD 할때 체크(세션2), 맥/리눅스용
        ├── gradlew.bat                 : 빌드 명령어 기술되어 있음 -> CI/CD 할때 체크(세션2), 윈도우용
        ├── settings.gradle
        └── (*)src
            ├── main                    : 개발 코드
            │   ├── java
            │   │   └── org
            │   │       └── example
            │   │           └── demoex
            │   │               ├── DemoexApplication.java
            │   │               └── controllers
            │   │                   └── HomeController.java
            │   └── resources           : 리소스, html/css/js or 리액트포함시킬경우, 이미지, 환경변수파일
            │       ├── application.properties  : 환경변수파일 (application.properties|yaml)
            │       ├── static          : 정적데이터 위치 (css, js, 이미지등등)
            │       └── templates       : html 파일 위치, 타임리프(템플릿엔진)가 기본으로 인식하는 위치임
            │           └── index.html
            └── test                    : 단위테스트 진행시,TDD(테스트 주도 개발방법론),CI/CD 중요하게사용
                └── java
                    └── org
                        └── example
                            └── demoex
                                └── DemoexApplicationTests.java  : 단위 테스트 직접 작성, 추가 가능
        ```

# 라이브러리 추가
    - build.gradle > dependencies 에 추가
        - 검색 : https://mvnrepository.com/
        - 적용

# 프로젝트 생성시 템플릿
    - 프로젝트명, 경로 설정
    - type -> 지정후 프로젝트 경로내 빌드 파일
        - Gralde G..    : build.gradle <- 2020년 이후에 많이 보임
        - Gralde Kotlin : build.gradle <- 2020년 이후에 많이 보임
        - maven         : pom.xml      <- 레거시방식

# 개발 도구 지원 - devtools
    - spring-boot-devtools : 감시 -> 소스 수정 -> 감지 -> 재가동
    - lombok : 코드 자동 완성(getter...)
    - live reload++: 브라우저내에서 해당 페이지 감시 -> 변경이 확인(재가동) -> 새로고침 자동 처리
        - 크롬 익스텐션
        - https://chrome.google.com/webstore/detail/livereload%20%20/ciehpookapcdlakedibajeccomagbfab
        - 구글에서 미지원( 업데이트 이슈)

# 컨트롤러
- 스트링부트가 요청을 받으면 -> 요청 URL 분석 -> 누가 처리할것인지 판단 -> 해당 요청을 해당 처리한는 모듈에 전달
- 컨트롤러는 특정 URL을 처리하도록 구성 -> 라우팅 과정
    - 종류 (어노테이션으로 구분)
        - @Controller     : 
            - html을 랜더링하여 응답하는 방식, 텍스트 응답도 가능                
            - 응답 형식 : html
            - SSR 처리
                - 전통적인 웹서비스
                - SB : html/css/js 응답
        - @RestController : 
            - 화면 X, html/css/js X -> open api
            - 응답 형식 : json/xml
            - CSR 처리와 세트로 구성이됨
                - 모바일/리액트(SPA) <-> SB
- URL 구성
    - 구분
        - 직접 url 개별적 부여 (일일이)
        - prefix를 부여하여 ~/prefix/~ 하위로 부여
    - 방식
        - 기획서 작성 -> 필요한 페이지와 URL 정리 -> 프로젝트 시작시 가장 처음에 구성!! (컨셉)
    - auth(인증) 라는 prefix를 가진 컨트롤러 생성
        - prefix는 다른 플랫폼(블루프린트 라고도함) -> 업무 범위 표현!!
        - http://localhost:8080/auth/~
        - A라는 개발자는 인증 모듈 개발, 회원가입, 로그인, 로그아웃, 등등... 구성 개발
    - 하위 주소
        - ~/auth/login
        - ~/auth/join
        - ~/auth/signup
        - ~/auth/logout ...

- 데이터 전달(전송)
    - client -> server 데이터 전달
    - 전달 방법(method)
        - (*)get, (*)post, put, .....
    - 클라이언트 전송 방식 (프런트엔드)
        - html
            - form 전송 : get, post 사용
            - a 태그 : 링크 전송, get
        - js
            - ajax 사용
                - axios 사용
                - fetch 사용
    - 서버는 어떻게 데이터를 받는가(수신)?
        - 결론 : 요청(request) 객체를 타고 전달됨
        - 코드 표현
            - 메소드 방식
                - @GetMapping()     : get 데이터 획득
                    - 브라우저 => 주소창 => URL 입력=> 엔터 : get 방식요청
                - @PostMapping()    : post 데이터 획득
                    - 보안 중요, 전달 데이터가 노출되면 x, 대량 데이터 전송(파일 업로드)
                    - html form 혹은 ajax로 사용, 요청 테스트 도구(POSTMAN, Thunder Client...)
                    - Thunder Client (요청 테스트용도)
                        - vscode extension 설치
                        - 번개 아이콘 클릭 > 진입 > new Request 클릭    
                            - POST 선택
                            - http://localhost:8080/auth/signin
                            - SEND 버큰 클릭
                            - 화면 하단 200 OK (요청 테스트 확인)
                            - 데이터 세팅
                                - Body > Form
                                    - uid,  값
                                    - upw,  값 
                            - SEND 버튼 클릭 => 요청
                - .... : ...
            - url path(타 플랫폼 동적 파라미터) 방식
                - 주소(URL)타고 전송
                - 여러 메소드 방식과 조합 가능함

- 템플릿 엔진
    - @Controller에서만 해당
    - 타임리프 제품 사용
        - thymeleaf.org
        - modern server-side Java template engine
        - 관련 문법은 필요시 등장시킴 
    - html + 데이터 => 동적으로 html 재구성 => 응답용 html 완성시키는 템플릿 엔진의 한 종류
    - 기본적인 처리 문법은 실제 적용할때 체크
        - 게시판 구현할때 사용

# 롬복
- getter, setter, 생성자, 빌더등 자동으로 생성해주는 라이브러리
- 패키지 dto 준비
    - News.java 생성

# RestAPI, @RestController
- 목적
    - json을 응답하는 구조 체크

- 구성
    - ~/controllers/ApiController 생성

# 스프링부트기반 데이터베이스 연동 -> 게시판 구성
- java의 DB 연동
    - JDBC -> 컨넥션풀 (기법) -> iBatis -> myBatis -> JPA
- (*)JPA
    - 2020년 전후로 많이 사용, 신규 프로젝트에서 주로 사용
    - ORM 방식 (객체로 적용하여, 데이터 1개=> 객체 1개와 대응하는 구조), 객체로 관리
    - SQL을 잘 몰라도 적용가능, 디비 교체도 수월함
- myBatis
    - 레거시 시스템 많이 적용되어 있음
    - 2010년 이후 많이 사용됨, 전자전부프레임웍, 등등 다 대부분 구성
    - SQL 중점으로 두고 개발, SQL 쿼리 작성 중요!!
    - 금요일날 별건으로 체크
- 데이터베이스 
    - 내장형 DB (*)h2 사용
        - 경량, 별다른 세팅없이 바로 사용가능, JPA 기반인 경우 마이그레이션 가능(다른 디비로)
    - 상용제품
        - 오라클, (*)mysql/mariadb, postgre, ...
- H2 세팅
    - build.gradle 라이브리 세팅 (초기 프로젝트 생성시 완료됨)
        ```
            // 서버 구동시(런타임시) 라이브러리 작동
            runtimeOnly 'com.h2database:h2'
        ```
    - application.properties 환경설정
        ```
            spring.application.name=demoex

            # 1. H2 database 설정
            # 브라우저에서 접속하여 sql 실행
            spring.h2.console.enabled=true
            # 브라우저 접속 주소
            spring.h2.console.path=/h2-console

            # 2. database connect 파트
            # app_db 는 커스텀이름
            # C:\Users\사용자명\app_db.mv.db 자동생성(생성 않되면 수동처리)
            spring.datasource.url=jdbc:h2:~/app2_db
            # 접속시 드라이버 설정
            # spring.datasource.driverClassName=
            spring.datasource.driver-class-name=org.h2.Driver
            # 접속계정
            spring.datasource.username=sa
            spring.datasource.password=

            # 3. JPA 설정
            # 데이터베이스 엔진 종류 설정
            spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
            # JPA -> 사용이득 -> 테이블로 알아서 생성해준다!!
            # 엔티티 클레스 작성 -> 매칭되는 테이블이 자동 생성됨 !!
            # 단 데이터베이스는 직접 생성!!
            spring.jpa.hibernate.ddl-auto=update


        ```
- 디비 접속
    - 서버 가동 중이여야 함
    - http://localhost:8080/h2-console

- JPA를 이용한 데이터베이스 프로그램
    - 코드 관점
        - workflow (기본, 표준)
            - controller <-> service <-> dto <-> repository <-> entity <-> jpa <-> database
            - 일부 가감될 수는 있다
    
    - controller
        - 클라리언트의 요청을 처리하는 진입점
        - 라우트 처리의 결과물
        - http 요청을 받아서 절적한 서비스 계층으로 호출, 결과를 받아서 반환(응답) 담당
        - (*)요약
            - 요청 처리, 파라미터 검증/추출, 적절한 서비스 호출, 응답 생성/반환
        - 필요시 dto는 노출됨, 단 엔티티 x
    
    - service
        - (*)비즈니스 로직 처리 계층
        - ex) 검색, 로그인, 로그아웃, 회원가입, 글등록, 글수정, ... -> sql 실행
        - 필요시 여러 API, 라이브러리 동원 처리
        - 요약
            - 컨트롤러의 요청을 받아서 처리, 결과를 반환
            - 데이터는 DTO대상으로 처리

    - dto (Data Transfer Object)
        - 서비스내에서 다루는 데이터
        - (*)엔티티와 데이터 교환 역활
        - 왜?, 엔티티는 디비의 테이블에 1개의 데이터와 각각 연결되어 있음
            - 엔티티가 수정되면, 디비상 테이블의 데이터로 수정이된다!! (동기화되어 있음)
            - 보안에 취약함(엔티티)
            - 엔티티는 절대로 컨트롤러에 노출되면 않됨
        - 엔티티 -> dto 를 통해서 데이터만 전달, 연결 고리가 끊김 -> 보안 처리할수 있음
        - (*)보안때문에 사용
        - 종류 
            - db
                - 엔티티와 데이터 교환용
            - rest api
                - 요청 데이터 대체용(JSON)
                - 응답 데이터 대체용(JSON)

    - repository
        - JPA를 이용하여 데이터베이스와 상호 작용
        - 내부적으로 (*)SQL 담당
        - 자동 구성해줌 -> 메소드 사용, 단 복잡한 쿼리는 필요시 직접 구성가능함
        - 엔티티 객체를 사용하여 CRUD 작업

    - entity
        - (*)데이터베이스의 테이블과 매핑이 되는 객체
        - 엔티티는 곧 테이블이다
        - 엔티티 객체 1개는 곧 데이터 1개이다 (1대 1로 매핑)
        - ORM(객체 관계 매핑)을 실제로 구현한것
        - 보안에 취약
        - 클라이언트에게 노출되면 않됨, 최대 Service까지만 사용가능, controller 사용 x

    - jpa
        - Java Persistance API
        - (*)자바 표준 ORM 기술
        - 객체와 관계형 데이터베이스 간의 매핑 제공
            - JPA 사용은 데이터베이스를 자바에서 객체 지향으로 관리하겠다 의도!!, SQL 사용을 최대한 배제

    - database
        - RDB

- 구성 실습
    - 1. 패키지 구성
        - 방식 (대략 2가지로 존재)
            - 비즈니스 로직별로 구성
                - auth or auth>controllers
                    - *Controller.java
                    - *Service.java
                    - ...
                - main
            - (*)자바파일의 용도별로 구성
                - controllers  
                    - *Controller.java      
                - services
                    - *Service.java
                - dto
                - repositories
                - entities
    
    - 2. ERD 구성
        - ERD 
            - 데이터 모델링 분야의 표현방식
            - 개체-관계 모델이라는 구조화 처리를 표현한 것
            - 툴에 상관없이 프로젝트 결과물에는 반드시 포함!!
        - 데이터베이스 모델링/ERD 테이블 정의 기본
        - 목표 : 게시판
            - 글작성, 댓글작성 하는 게시판 
            - draw.io라는 툴에서 작성
            - 필요한 기능은 툴에서 설정
        - 테이블 설계
            - 개발하면서 계속 수정 가능!!(단기간)
                - 단기간에는 지속적 수정 발생될 있음
            - 개발의 첫작업!!
    
    - 3. 엔티티 구성
        - 현재 요구사항은 테이블 2개 -> 엔티티 2개 생성
        - 테이블명와 동일하게 구성, 필요시 변경 가능
        - 자바 파일생성
            - ~/entities/Post.java, Review.java

    - 4. 게시물 화면으로 보이기 (게시판 뷰)
        - Post 테이블의 내용을 html로 표시
            - 4-1. 더미 데이터 삽입후 테스트 -> h2 입력
            - 4-2. 데이터를 html뿌리는 과정을 통해 db 연동 플로우 체크
                - 4-2-1. PostController 에서 /post/list, get방식 준비
                        - ~/templates/test/post_list.html
                        - (*)controller <-> service <-> dto <-> repository <-> entity <-> jpa <-> database

                - 4-2-2. 서비스 구성
                        - controller <-> (*)service <-> dto <-> repository <-> entity <-> jpa <-> database

                - 4-2-3. dto 구성
                        - controller <-> service <-> (*)dto <-> repository <-> entity <-> jpa <-> database

                - 4-2-4. repository 구성
                        - controller <-> service <-> dto <-> (*)repository <-> entity <-> jpa <-> database

                - 4-2-5. 타임리프 템플릿 엔진 적용
                        - 화면에 게시물의 내용 출력
            
    
    - 5. 타임리프 문법 기본 문법
        - 템플릿 제공 (편집 내용 확인)
            - 개발 시작 (시나리오 가정)
                - 기획 -> 기획서 작성, 스토리 보드, ERD 구성
                - 개발팀 : 개발 -> 디자인 입히는 과정이 필요!! -> 5-1. (*)템플릿 제공, 위치시킴
                    - ~/resources/static
                        L dist
                            L css
                            L img
                            L js
                        L pluhins
                            L * : js 라이브러리
                    - ~/resources/templates
                        L board
                            L *.html : 글목록/쓰기/수정/삭제, 리뷰..
                        L lib
                            L *.html : 레이아웃 전용 (기본 템플릿)
                - DBA : 모델링, 테이블등 작업
                - 디자인 : 시안(a,b,c,d) -> html 코딩, css 작업등등
                - js : 프런트 진행
                - 

        - 5-2. 각 용도에 맞게 라우트 처리
            - 실습 : 5분
                - 아래 URL 모두 준비, 기본값 세팅, Get방식 정리
            - HomeController
                - 대시보드 메인 : ~/dashboard
            - PostController                
                - 글 목록(게시판): ~/post/list
                - 글 작성하기   : ~/post/create
                - 글 상세보기   : ~/post/detail/{id}
                - 글 수정하기   : ~/post/modify/{id}
                - 글 삭제하기   : ~/post/delete/{id}
            - ReviewController
                - 리뷰 쓰기     : ~/review/create
                - 리뷰 목록     : ~/review/list
                - 리뷰 수정하기  : ~/review/modify/{id}
                - 리뷰 삭제하기  : ~/review/delete/{id}
        
        - 5-3. 템플릿 구성 이해
            - 타임리프 문법 정리
            - 레이아웃 적용법, 조각 대체법 확인
            - html 구성 내용 이해

        - SSR 습득

    
    - 6. 게시판 기능 완성 -> CRUD 연습
        - (*)글 목록 : 기능 + 디자인 결합
                - PostController
                - board/post_list_content.html
                - 더미데이터 입력
                ```
                    insert into post ( subject, content, create_date )  
                    values ( '제목3', '내용3', now());
                ```
            - 글 작성 : 기능 + 디자인 결합 - insert, 입력폼 - 유효성검사(마지막세팅), form, input
                - 폼 전송 시도
                ```
                    There was an unexpected error (type=Method Not Allowed, status=405).
                    Method 'POST' is not supported.
                    org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' is not supported

                    => 405 오류. 해당 URL은 존재한다. 그러나 그런(여기서는 POST) 메소드는 지원하지 않는다!!
                    => 해결, 해당 메소드 방식으로 라우트 추가!!, URL 동일
                ```
                - 입력 => 유효성 검사 추가 (백엔드 중심)
                    - 목표 : 정확한 데이터를 입력받겠금 유도 -> 보안이슈, 운영이슈
                    - 절차
                        - 검사폼 클레스 구성 -> 컨트롤러(내부 특정 메소드) 적용 -> html 반영
                        - 검사폼 클레스 -> ~/form/PostForm 구성
                        - 컨트롤러
                            - 입력폼이 보이는 화면(GET)
                                - 검사폼 클레스 적용
                                    - 검증 결과후 에러나면 표기 하기 위해
                                - ~/create => ~/create2
                                - 글쓰기 링크 조정!!   
                        - html 반영
                            - post_form_content.html
                                - form 클레스
                        - 컨트롤러
                            - 입력폼에서 전달한 데이터를 처리하는 파트(POST)
                                - 검사폼 클레스 적용
                                    - 검증 용도
                                - ~/create => ~/create2
                        

            - 글 수정 : 기능 + 디자인 결합 - update, 입력폼 - 유효성검사, form, input
                - 컨트롤러에서 요청시 -> 해당 id에 일치하는 Post 데이터 획득 -> dto등 담아서
                - PostForm 객체에 세팅하여 -> post_form_content.html <form>에 반영
                - 입력과 수정은 화면이 거의 동일함(같이 사용, 여기서는)
                - workflow
                    - 컨트롤러 구성 (GET)
                        - 수정 화면
                    - 컨트롤러 구성 (POST)
                        - 수정 하기
                        - 글 등록과 거의 동일 (최종 처리(디비처리)만 다음)

            - 글 삭제 : 기능 + 디자인 결합 - delete, 경고창(실수로 누를수 있다) + JS동반 -> 삭제처리

            - (*)글 상세보기
                - (*)리뷰 목록 보기 : 기능 + 디자인 결합
                    - 더미데이터 
                    ```
                        insert into review
                        (content, create_date, post_id)
                        values
                        ('댓글 6', now(), 3);
                    ```
                    - 리뷰 작성 : 기능 + 디자인 결합 - insert, 입력폼 - 유효성검사, form, input
                        - (*)오후 학습 정리시간에 시도해 보기

                    - 리뷰 수정 : 기능 + 디자인 결합 - update, 입력폼 - 유효성검사, form, input
                        - 리뷰폼 클레스 구성
                        - 리뷰 수정 페이지, 리뷰폼 전달(GET)                        
                        - html 수정 (board/review_form)
                            - th:object, 에러처리, th:field
                        - 리뷰 수정 실제 처리(Post)
                            - 수정처리
                            - 화면포워딩

                    - 리뷰 삭제 : 기능 + 디자인 결합 - delete, 경고창(실수로 누를수 있다) + JS동반 -> 삭제처리
            
        
        - 7. 목표
            - 게시판 완성 (풀 버전은 아님 )
                - 검색, 페이징 생략 => 향후 업데이트해서 제공

            - 스프링시큐리티를 이용한 인증
                - (*)세션 객체
            
            - 내일 목표
                - JWT(토큰) or oAuth2(네이버, 카카오, 구글, 애플등 인증된 외부 기관을 통해 로그인)
                - AOP/로깅
                - RestApi + swagger(테스트)
                - maven + myBatis -> 프로젝트 구조
                - 파일 업로드 (이미지, 기타 파일)
                - 웹소켓 (챗봇)
                
                - 프로젝트 구조
                    - SSR only springboot <-> DB
                    - CSR react <-> springboot(restapi) <-> DB
                    - react(CSR) in SSR springboot <-> DB
            
            - 프로젝트 첫날
                - 사후 테스트
                - 팀빌딩
                - 깃헙 팀작업(프로젝트 첫날) 진행예정

# 스프링 시큐리티
- 개요
    - 스프링 프레임워크의 하위 프레임워크
    - 용도 : 인증, 인가(권한), 보안 프레임워크, 필수 적용
    - 버전 : 6.x 사용

- 특징
    - 인증
    - 인가
    - 보안정책설정
    - 세션관리
    - JWT, oAuth2 지원
    - CSRF 공격 방어

- 목적
    - 로그인, 로그아웃
    - 회원가입, 회원수정 x
    - 페이지별 접근 여부등 정책 적용
        - 인증이 있어야만 접근가능한 페이지
        - 인증 없이 접근할수 있는 페이지
        - 권한(인가)에 따라 메뉴가 상이하게 노출

- 세팅
    - 외부 라이브러리 
    ```
        // 스프링 시큐리티 프레임웍
        implementation 'org.springframework.boot:spring-boot-starter-security'
        
        // 타임리프와 연동하는 스프링시큐리티
        implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
        
        // 단위 테스트 스프링시큐리티
        testImplementation 'org.springframework.security:spring-security-test'

    ```

- 구조
    - 설정 (config)
        - 시큐리티 전반적인 정책 기술
    - 컨트롤러
        - 홈페이지 (인증후 접근 가능하게 설계)
        - 회워관리 페이지(로그인, 로그아웃, 회원가입,..)
    - 엔티티
        - User 테이블
    - Dto
        - User 관련 통신/디비용(가능하면 겸용)
    - 레퍼지토리
        - 회원관련 SQL 처리
    - 서비스
        - User 서비스 (DB용)
        - UserDetail 서비스 (인증정보등 용도)
    - 유틸
        - 컴포넌트 등록 -> DI 처리
        - 인증/권한 정보 메소드 제공

- 구성 
    - 패키지 구성
    - 패키지 내 세부 파일(자바) 구성
    - 엔티티 상세 작업
    - application.properties -> yml 파일로 변경 처리(계층 표현)
    - 상세구현

# 소스기반 개별 기술 확인
    - backend_1
        - restapi + swagger + JS : 차트 그리기
        - 웹소켓을 이용한 실시간 채팅 (네이버 뉴스 검색) : 챗팅방식
    - aop_test
        - aop
        - 로그/로깅
        - 관리및 유지보수, 편의성
    - test_mybatis
        - maven 빌드 (pom.xml)
        - 디비연동 mybatis 
    - upload_test
        - 파일업로드 (ajax, html form)