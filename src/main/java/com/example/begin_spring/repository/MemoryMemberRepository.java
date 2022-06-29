package com.example.begin_spring.repository;

import com.example.begin_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 자바 자료구조 Map을 사용해서 메모리에 Member 저장하는 Repository
public class MemoryMemberRepository implements MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 자체적으로 Long 필드를 가져 id로 사용할 수 있게끔

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}