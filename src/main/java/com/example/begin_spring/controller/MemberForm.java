package com.example.begin_spring.controller;

/**
 * post방식으로 값이 오고 갈 때 사용되는 form.
 * DTO.
 */
public class MemberForm {
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
