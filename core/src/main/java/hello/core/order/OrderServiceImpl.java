package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();     //회원 정보
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  // 디스카운트 정책

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);                        //회원정보를 찾음
        int discountPrice = discountPolicy.discount(member, itemPrice);             // 할인 가격을 가져옴

        return new Order(memberId, itemName, itemPrice, discountPrice);             // 주문 객체 생성
    }
}
