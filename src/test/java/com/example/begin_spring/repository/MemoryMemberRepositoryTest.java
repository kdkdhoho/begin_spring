package com.example.begin_spring.repository;

import com.example.begin_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 모든 test들은 순서 상관없이 실행되더라도 각각 서로에게 영향을 주면 안된다.
     * 따라서 각 test가 끝나고 store을 비워줘야 한다.
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    /**
     * @Test 필수!
     */
    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("dongho");

        // when
        repository.save(member);

        // then
        assertThat(member).isEqualTo(repository.findByName("dongho").get());

        afterEach();
    }

    @Test
    void findByName() {
        // given
        Member member = new Member();
        member.setName("doyeon");

        // when
        repository.save(member);
        Member findMember = repository.findByName("doyeon").get();

        // then
        assertThat(member).isEqualTo(findMember);

        afterEach();
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("dongho");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("doyeon");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}