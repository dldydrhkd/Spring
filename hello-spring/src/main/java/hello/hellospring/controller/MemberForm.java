package hello.hellospring.controller;

public class MemberForm {   // html에서 응답받은 이름을 담기 위한 class
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
