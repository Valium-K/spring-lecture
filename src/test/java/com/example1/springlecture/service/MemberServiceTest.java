package com.example1.springlecture.service;

import com.example1.springlecture.domain.Member;
import com.example1.springlecture.repository.MemberRepository;
import com.example1.springlecture.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memoryMemberRepository;
    MemberService memberService;


    // 각각의 테스트를 하기 전 DI를 해
    // 각각의 테스트가 독립적으로 실행되게 한다.
    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("member1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member foundMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());

    }

    @Test
    public void 중복회원_검증() {
        Member member1 = new Member();
        member1.setName("member");

        Member member2 = new Member();
        member2.setName("member");

        memberService.join(member1);

        // 해당 코드는 아래 주석된 코드와 같다고 보면 된다.
        IllegalStateException ise = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(ise.getMessage()).isEqualTo("동일한 이름은 가입불가.");


//        try {
//            memberService.join(member2);
//            fail();
//        }
//        catch (IllegalStateException ise) {
//            assertThat(ise.getMessage()).isEqualTo("동일한 이름은 가입불가.");
//        }

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}