package com.example1.springlecture.service;

import com.example1.springlecture.domain.Member;
import com.example1.springlecture.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


// 스프링 컨테이너에 등록
// @Service
// service는 repository의 기능을 controller와 중간에서 연결해주는 역할이다.
// 변수명이나 메소드명은 전문용어가 아닌 쉽게 통용되는 단어로 구성해야한다.

// JPA의 트랙젝션관리
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // 생성시 의존성 주입
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public long join(Member member) {
        // 중복회원 검증
        validatingDuplicatedMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        memberRepository.findById(1l);
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validatingDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("동일한 이름은 가입불가.");
            });
    }
}
