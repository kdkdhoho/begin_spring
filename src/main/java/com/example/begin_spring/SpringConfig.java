package com.example.begin_spring;

import com.example.begin_spring.repository.JdbcMemberRepository;
import com.example.begin_spring.repository.JdbcTemplateMemberRepository;
import com.example.begin_spring.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 직접 빈 등록 후 DI.
 * Repository의 경우 저장소에 따라 객체가 변동될 수 있는 가능성이 있으므로
 * 수동 등록을 통해 Dependecy Injection을 해준다.
 */
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
