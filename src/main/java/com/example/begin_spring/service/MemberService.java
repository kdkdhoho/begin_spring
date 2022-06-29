package com.example.begin_spring.service;

import com.example.begin_spring.domain.Member;
import com.example.begin_spring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 같은 이름 회원 가입 x
        validateDuplication(member);

        memberRepository.save(member);
        return member.getId();
    }

    // Ctrl + Alt + Shift + T 눌러서 메소드로 추출
    private void validateDuplication(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회(Id)
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }
}
