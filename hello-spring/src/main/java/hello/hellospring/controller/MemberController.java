package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;
    // 처음 spring에서 생성을 할 때 memberService를 생성자로 받아서 사용한다.

    @Autowired  //spring container에 있는 memberService를 자동으로 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
