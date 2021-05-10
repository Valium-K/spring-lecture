package com.example1.springlecture.service;

import com.example1.springlecture.domain.Member;
import com.example1.springlecture.repository.MemberRepository;
import com.example1.springlecture.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

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
