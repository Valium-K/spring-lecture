스프링 DB 접근 기술
=================
## H2
* 간단한 학습용
* 첫 실행시 JDBC URL을 설정 후 DB를 만들고 이후 접속시  
    `jdbc:h2:tcp://localhost/[만든 DB 경로]`로 설정 후 접속해야한다.

## JDBC
* JDBC Driver 추가 및 설정 방법
  ### build.gradle - 의존관리
    ```
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-jdbc'
        runtimeOnly 'com.h2database:h2'
    }
    ```
  ### application.properties - 설정
    ```
    spring.datasource.url=dbc:h2:tcp://localhost/[만든 DB 경로]
    spring.datasource.driver-class-name=org.h2.Driver
    ```
  
  ### JDBCMemberRepository
    ```java
    public class JDBCMemberRepository implements MemberRepository {
        private final DataSource dataSource;
    }    
}
    ```