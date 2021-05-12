package com.example1.springlecture.repository;

import com.example1.springlecture.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속하는 인터페이스가 있으면
// SpringDataJpa 가 해당 인터페이스를 자동으로 구현하고 자동으로 Spring Bean 을 등록한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
