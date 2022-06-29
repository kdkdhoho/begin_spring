package com.example.begin_spring.service;

import com.example.begin_spring.domain.Member;
import com.example.begin_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /**
     * MemoryMemberRepository memberRepository = new MemoryMemberRepository(); 로 객체 생성을 따로 두 번 하는 것을 막기 위해
     * => 실행할 때마다 같은 객체를 넣어주기 위해
     */
    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        Member member = new Member();
        member.setName("dorang");
        memberService.join(member);

        Member member2 = new Member();
        member2.setName("dorang");
        /*try {
            memberService.join(member2);
            fail("중복 회원 검증 실패");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("dongho");
        member2.setName("doyeon");

        memberService.join(member1);
        memberService.join(member2);

        List<Member> result = memberService.findMembers();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findById() {
        Member member = new Member();
        memberService.join(member);

        Member findMember = memberService.findById(member.getId()).get();

        assertThat(findMember).isEqualTo(member);
    }
}