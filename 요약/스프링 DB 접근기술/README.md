스프링 DB 접근 기술
=================

# DB

------------------------------

## H2
* 간단한 학습용
* 첫 실행시 JDBC URL을 설정 후 DB를 만들고     
    로컬 DB생성: `jdbc:h2:[만들 DB 경로]` 이후 접속시     
    `jdbc:h2:tcp://localhost/[만든 DB 경로]`로 설정 후 접속해야한다.

# ORM

------------------------

## JDBC
* 순수 JDBC는 잘 사용하지 않는다 참고만하자.
  
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
    
        public JDBCMemberRepository(DataSource dataSource) {
            this.dataSource = dataSource;
        }
    }
    ```

## Spring JDBC Template
* 순수 JDBC대신 사용한다.
* 순수 JDBC와 동일한 환경설정으로 사용 가능
* 순수 JDBC의 반복적 코드를 대부분 제거해준다.
* 그러나 Query는 직접 작성 해야한다.

### 사용예제
* [repository/JDBCTemplateMember](/src/main/java/com/example1/springlecture/repository/JDBCTemplateMemberRepository.java)
* [service/SpringConfig](/src/main/java/com/example1/springlecture/service/MemberService.java) - JDBC 주석
## JPA
* 기존 반복코드 + SQL문도 직접 만들어 실행해준다.
* 객체 중심의 설계로 전환가능하다.
* JPA는 인터페이스이고, 여러업체가 이를 구현한다(ex. RedHat의 Hibernate).
* DB를 변경-저장 한다면 service 클래스에 `@Transactional`을 꼭 붙여야한다. 
  ### build.gradle - 의존관리
    ```
    dependencies {
        // JPA settings
        // 참고로 spring-boot-starter-data-jpa는 starter-jdbc를 포함한다.
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        runtimeOnly 'com.h2database:h2'
    }
    ```
  ### application.properties - 설정
    ```
    ! JPA가 만드는 sql문을 보겠다.
    spring.jpa.show-sql=true
    ! 이미 Table을 만들었기에 JPA가 자동으로 DDL을 작성하도록 하지 않겠다.
    spring.jpa.hibernate.ddl-auto=none
    ```
  ### 사용 예제
    * [domain/Member](/src/main/java/com/example1/springlecture/domain/Member.java)
    * [repository/JpaMemberRepository](/src/main/java/com/example1/springlecture/repository/JpaMemberRepository.java)
    * [service/MemberService](/src/main/java/com/example1/springlecture/service/MemberService.java)
    * [service/SpringConfig](/src/main/java/com/example1/springlecture/service/MemberService.java) 

## Spring JPA
* 스프링에서 JPA 사용을 더욱 간결하게 해준다.
* JPA 설정을 그대로 사용
* 어디까지나 JPA 기반이므로 JPA를 이해한 후 사용하는것을 추천
* CRUD기능도 Spring JPA가 제공하고, Repository의 구현클래스 없이 인터페이스 만으로 개발가능.
