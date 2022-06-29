package com.example.begin_spring.domain;

public class Member {

    private Long id;
    private String name;

    /**
     * 임의로 추가해본 필드임.
     * 추가했을 때 MemberService, MemberRepository 건드릴 필요 x
     * 다만 MemberForm과 Controller에서 member.set~~ 에서 수정 필요
     */
    private String sex = "MAN";

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id = " + getId() + " name = " + getName() + " sex = " + getSex() + "}";
    }
}
