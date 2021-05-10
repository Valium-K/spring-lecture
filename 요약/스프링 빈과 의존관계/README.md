스프링 빈을 등록하는 2가지 방법
==========================
1. 컴포넌트 스캔과 자동의존 방법    
    * `@Component` 애노테이션이 있으면 스프링빈이 자동 등록됨
    * `@Component` 를 포함하는 다음 애너테이션도 자동으로 등록된다.
        * `@Controller`
        * `@Service`
        * `@Repository`
    * 컴포넌트간 연결은 생성자에 `@Autowired`를 사용하여 자동 DI한다.
    * 컴포넌트 스캔은 `@SpringBootApplication`가 있는 해당 패키지와 그 하위 경로만 스캔한다.
   
2. 자바 코드로 직접 스프링 빈 등록
    * 설정파일을 만들어 직접 등록한다.(@Controller 애노테이션은 꼭 필요함)
    ```java
    @Configuration
    public class SpringConfig {
   
        @Bean
        public MemberService memberService() {
            return new MemberService(memberRepository());
        }

        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }
    }
    ```
   
* 참고
   * XML 설정방식은 최근 잘 사용하지 않음.
   * DI 방법은 필드 주입, setter 주입, 생성자 주입이 있고  
     Bean이 등록되면 바꿀일이 없기에 생성자 주입을 주로 사용한다.
     
   * 일반적으로 정형화된 컴포넌트는 컴포넌트 스캔을 이용하고,  
      그렇지 않거나 상황에 따라 구현체를 변경하는 경우에는 설정으로 직접 스프링 빈을 등록한다.
     
   * @Autowired는 스프링 컨테이너에 관리되는 객체에만 사용 가능하다.