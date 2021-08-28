package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //test 할 때마다 밑에 함수를 실행해준다.
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();   // 새로운 멤버 생성
        member.setName("spring");       // spring이라는 이름으로 저장

        repository.save(member);        // repository에 저장

        Member result = repository.findById(member.getId()).get(); //repository에서 spring이름으로 멤버를 찾아옴
        assertThat(member).isEqualTo(result);       // 비교
    }

    @Test
    public void findByName(){
        Member member1 = new Member();  // 새로운 멤버1  생성
        member1.setName("spring1");     // 이름은 spring1으로 생성
        repository.save(member1);       // 레포에 저장

        Member member2 = new Member();  // 새로운 멤버2 생성
        member2.setName("spring2");     // 이름은 spring2로 생성
        repository.save(member2);       // 레포에 저장

        Member result = repository.findByName("spring1").get(); // 이름이 spring1인 멤버를 가져옴

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();     // 배열로 반환을 받음

        assertThat(result.size()).isEqualTo(2);     // result의 사이즈를 비교
    }
}
