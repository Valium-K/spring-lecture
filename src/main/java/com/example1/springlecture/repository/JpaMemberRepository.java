package com.example1.springlecture.repository;

import com.example1.springlecture.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA는 EntityManager 로 모든것이 동작한다. (EntityManager가 DataSource, DB connection을 Managing 한다.)
    // 이는 'org.springframework.boot:spring-boot-starter-data-jpa' 설정을 했기에 가능하다.
    // 이 설정으로 스프링 부트가 자동으로 EntityManager를 생성하기에 Repository에서 생성자 DI 받기만 하면된다.
    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        // PK기반, 결과가 하나인 것인 짧은 코딩이 가능하다.
        // 그 외에는 JPQL로 짜면된다.
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = entityManager.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL로 객체를 대상으로 Query를 날리는 언어이다.
        // Member 객체 자체를 수평연산한다.
        return entityManager.createQuery("select m from Member as m", Member.class)
                .getResultList();
    }
}
