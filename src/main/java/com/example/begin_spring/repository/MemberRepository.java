package com.example.begin_spring.repository;

import com.example.begin_spring.domain.Member;

import java.util.List;
import java.util.Optional;

// Member 도메인을 DB(or memory)에 저장하기 위한 Repository의 interface
public interface MemberRepository {

    // 회원 저장
    Member save(Member member);

    // id로 회원 조회
    Optional<Member> findById(Long id);

    // name으로 회원 조회
    Optional<Member> findByName(String name);

    // 전체 회원 조회
    List<Member> findAll();
}
