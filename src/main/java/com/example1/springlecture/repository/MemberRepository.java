package com.example1.springlecture.repository;

import com.example1.springlecture.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// interface를 만들어 실제 DB가 정해지지 않은 경우에도 개발이 가능하도록 한다.
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
