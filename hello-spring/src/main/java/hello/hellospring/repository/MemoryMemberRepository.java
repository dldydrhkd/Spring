package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();   // 공유되는 변수는 concurrent HashMap을 사용하는게 더 좋다
    private static long sequence = 0L;      // 공유되는 변수이므로 atomiclong을 쓰는게 좋다

    @Override
    public Member save(Member member) {
        member.setId(++sequence);       // 시스템상의 id를 증가시켜주어 member에 id를 부여한다.
        store.put(member.getId(), member);  // store에 저장해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // null이 반환될 가능성이 있으므로 Optional을 사용한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    // 이름이 같은걸 반환한다.
                .findAny();             // 첫번째로 찾은것만 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());         // store에 들어있는 value값들 즉 member들이 list로 반환한다.
    }

    public void clearStore(){
        store.clear();
    }
}
