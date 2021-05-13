package com.example1.springlecture.repository;

import com.example1.springlecture.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// JpaRepository를 상속하는 인터페이스가 있으면
// SpringDataJpa 가 해당 인터페이스를 자동으로 구현하고 자동으로 Spring Bean 을 등록한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 메서드 이름으로 맞춰주면 된다. ex. findBy[찾을 속성]
    @Override
    Optional<Member> findByName(String name);

    Member save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
}
