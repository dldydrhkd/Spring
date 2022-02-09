package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberSerivce() {              // memberService 역할
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {     // memberRepository 역할
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){         // orderService 역할
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){         //  discountPolicy 역할
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
