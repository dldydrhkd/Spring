package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller     // controll annotation(주석)
public class HelloController{

    @GetMapping("hello")    //웹 어플리케이션에서 /hello라고 들어오면 밑에 hello 메서드를 호출해준다
    public String hello(Model model){
        model.addAttribute("data", "hello!!");  //MVC의 Model이다.
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody// html에 나오는 body tag가 아닌 http에서 헤더와 바디로 나뉘는 바디에 데이터를 직접 넣어준다는 뜻이다
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
}