package com.example1.springlecture.service;

import com.example1.springlecture.repository.MemberRepository;
import com.example1.springlecture.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 컴포넌트 스캔이 아닌 직접 스프링 빈을 등록해 설정함 (하지만 Controller는 애노테이션을 달아야함)
// 스프링이 실행 될 때 메소드가 실행되어 DI가 된다.
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
