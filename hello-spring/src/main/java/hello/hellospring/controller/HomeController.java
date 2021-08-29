package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")     // localhost:8080으로 들어오면 바로 home.html로 가게된다.
    public String home(){

        return "home";      // templates에 있는 home.html을 찾게 한다.
    }

}