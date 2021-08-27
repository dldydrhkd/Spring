package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);     // 회원을 저장
    Optional<Member> findById(Long id);     // id로 회원을 찾음
    Optional<Member> findByName(String name);   // 회원 이름으로 회원을 찾는다
    List<Member> findAll();         // 지금까지 저장된 회원 모두를 반환해준다
}
