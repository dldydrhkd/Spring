package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//    // spring이 application.properties를 보고 자체적으로 dataSource를 만들어준다.
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;

    private final MemberRepository memberRepository;

    @Autowired //생성자가 하나인 경우 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }   // SpringDataJpa가 만들어 놓은 구현체가 등록이 된다.

    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
