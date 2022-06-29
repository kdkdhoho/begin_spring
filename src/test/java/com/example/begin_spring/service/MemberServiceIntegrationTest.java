package com.example.begin_spring.service;

import com.example.begin_spring.domain.Member;
import com.example.begin_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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

    /**
     * 실패 => 다른 객체가 나옴
     */
    @Test
    @Rollback(value = false)
    void findById() {
        Member member = new Member();
        member.setId(1L);
        member.setName("doddoo");
        member.setSex("MAN");

        memberService.join(member);
        System.out.println("member = " + member);

        Member findMember = memberService.findById(member.getId()).get();
        System.out.println("findMember = " + findMember);

        assertThat(findMember).isEqualTo(member);
    }
}