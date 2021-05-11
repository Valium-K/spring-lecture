통합 테스트
=========
* 스프링 부트에서 제공하는 `@SpringBootTest`를 이용해 테스트 할 수 있다.
* `@Transactional`을 이용해 테스트 시작 전 commit 후 테스트 완료 후 rollback을 한다.(테스트 케이스에 붙었을 떄만 적용.)
* 유닛 테스트 - 통합 테스트 - 시스템 테스트 - 인수 테스트의 과정이 있다.
    
    ```java
    // @SpringBootTest 로 스프링을 통해서 통합테스트를 실행한다. 
    @SpringBootTest
    @Transactional
    class MemberServiceIntegrationTest {
    
        // 테스트는 편하게 필드 주입을 한다.
        @Autowired MemberRepository MemberRepository;
        @Autowired MemberService memberService;
    
    // 아래 코드는 @Transactional로 대체 가능하다.
    // 기본적으로 commit 후 rollback을 해 db를 복구하는 것이다.
    //    @BeforeEach
    //    public void beforeEach() {
    //        memoryMemberRepository = new MemoryMemberRepository();
    //        memberService = new MemberService(memoryMemberRepository);
    //    }
    //
    //    @AfterEach
    //    public void afterEach() {
    //        memoryMemberRepository.clearStore();
    //    }
    }
    ```