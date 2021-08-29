package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    // 처음 spring에서 생성을 할 때 memberService를 생성자로 받아서 사용한다.

    @Autowired  //spring container에 있는 memberService를 자동으로 연결시켜준다.
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")        // html에서 온 응답을 받는다
    public String create(MemberForm form){
        Member member = new Member();       // 새로운 멤버를 만든다
        member.setName(form.getName());     // 이름을 저장해준다.

        System.out.println(member.getName());
        memberService.join(member);         // member에 추가

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();  //member 목록을 members list에 담는다.
        model.addAttribute("members", members); //list members의 이름을 members라 하고 model에 담는다.
        return "members/memberList";
    }

}
