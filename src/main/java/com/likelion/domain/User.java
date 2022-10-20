package com.likelion.domain;
//User정보를 외부에서 입력받기 위해 User클래스 선언
//UserDao2클래스의 SELECT FORM문을 출력하는 get()메소드 만듦
public class User {
    private String id;
    private String name;
    private String password;

    public User() {
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}

