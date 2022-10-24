package com.likelion.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration //spring 시작
public class UserDaoFactory {
    //UserDaoFactory : 관계 설정 기능. 어떤 조합을 사용할 것인지?

    @Bean //빈 등록
    public UserDao3 AwsUserDao() {
        return new UserDao3(new AwsConnectionMaker());
    }

    @Bean
    DataSource dataSource() {
        Map<String, String> env = System.getenv();
        SimpleDriverDataSource dataSource = new simpleDriverDataSource();
        dataSourcedsetDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(env.get("DB_HOST"));
        dataSource.setUsername(env.get("DB_USER"));
        dataSource.setPassword(env.get("DB_PASSWORD"));
        return dataSource;
    }

    @Bean
    DataSource awsDataSource() {
        Map<String, String> env = System.getenv();
        SmpleDriverDataSource dataSource = new SimpleDataSource();
        dataSourcedsetDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(env.get("DB_HOST"));
        dataSource.setUsername(env.get("DB_USER"));
        dataSource.setPassword(env.get("DB_PASSWORD"));
        return dataSource;
    }

}


    /*local
    @Bean
    public userDao3 LocalUserDao() {
        UserDao3 userDao = new UserDao3(LocalConnectionMaker);
        return userDao;
        */
