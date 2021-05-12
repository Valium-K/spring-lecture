package com.example1.springlecture.service;

import com.example1.springlecture.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 컴포넌트 스캔이 아닌 직접 스프링 빈을 등록해 설정함 (하지만 Controller는 애노테이션을 달아야함)
// 스프링이 실행 될 때 메소드가 실행되어 DI가 된다.
@Configuration
public class SpringConfig {

//    // JDBC를 위한 의존성
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    // JPA를 위한 의존성
//    private EntityManager entityManager;
//
//    @Autowired
//    public SpringConfig(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Spring Jpa를 사용하는 member
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    // 직접 Repository를 추가
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        // return new MemoryMemberRepository();
//        // return new JDBCMemberRepository(dataSource);
//        // return new JDBCTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(entityManager);
//    }
}
