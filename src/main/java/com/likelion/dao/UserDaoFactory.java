package com.likelion.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //spring 시작
public class UserDaoFactory {
    //UserDaoFactory : 관계 설정 기능. 어떤 조합을 사용할 것인지?

    @Bean //빈 등록
    public UserDao3 AwsUserDao() {
        return new UserDao3(new AwsConnectionMaker());
    }
}

    /*local
    @Bean
    public userDao3 LocalUserDao() {
        UserDao3 userDao = new UserDao3(LocalConnectionMaker);
        return userDao;
        */
