package com.example1.springlecture.service;

import com.example1.springlecture.repository.JDBCMemberRepository;
import com.example1.springlecture.repository.MemberRepository;
import com.example1.springlecture.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


// 컴포넌트 스캔이 아닌 직접 스프링 빈을 등록해 설정함 (하지만 Controller는 애노테이션을 달아야함)
// 스프링이 실행 될 때 메소드가 실행되어 DI가 된다.
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JDBCMemberRepository(dataSource);
    }
}
