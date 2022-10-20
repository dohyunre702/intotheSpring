package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class) //스프링 테스트를 사용하기 위한 설정
@ContextConfiguration(classes = UserDaoFactory.class) //설정 정보 불러오기

class UserDao3Test {

    @Autowired //의존관계 자동주입
    ApplicationContext context; //ApplicationContext 인터페이스 선언

    UserDao3 userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    //@BeforeEach는 Juint5 이후 추가된 애너테이션
    void setUp(){
        this.userDao = context.getBean("awsUserDao", UserDao3.class);
        //중복을 제거하기 위해 Autowired에 user1,2,3를 정의하고 this로 사용
        this.user1 = new User("1", "Kim", "1123");
        this.user2 = new User("2", "Cho", "3321");
        this.user3 = new User("3", "Jang", "5532");

    }


    @Test
    void addAndGet() throws SQLException {
        User user1 = new User("1", "Kim", "1123");

        UserDao3 userDao = context.getBean("awsUserDao", UserDao3.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
//      User user = userDao.findById(id);
        assertEquals(1, userDao.getCount());
        User user = userDao.findById(user1.getId());

        assertEquals("DH", user.getName());
        assertEquals("DH", user.getPassword());
    }

    @Test
    //유저 데이터를 여러 개 넣어보고 삭제와 추가가 모두 정상 동작하는지 테스트
    void count() throws SQLException{
        //반복되는 부분
//        ApplicationContext context = new GenericXmlApplicationContext(
//                "applicationContext.xml");

        UserDao3 userDao = context.getBean("awsUdserDao", UserDao3.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        userDao.add(user2);
        assertEquals(2, userDao.getCount());
        userDao.add(user3);
        assertEquals(3, userDao.getCount());
    }

    @Test //예외조건에 대한 테스트
    void findById() {
        //BeforeEach의 적용에 따른 findById 테스트 리팩토링
        assertThrows(EmptyResultDataAccessException.class, () -> {
                    userDao.findById("30");
                });
    }
}

        /*
        @Test
        void addFindByIdTest() throws SQLException, ClassNotFoundException {
            //interface
            //UserDao3 userDao = new UserDao3(new LocalConnectionMaker());

            //interface를 자동으로 주입해주는 팩토리
            // UserDao3 userDao = new UserDaoFactory().localUsexrDao();

            //Spring 적용해 UserDao팩토리 불러오기
            User user = new User("8", "EternityHwan", "1123");
            userDao.add(user);

            User findUser = userDao.findById("8");
            Assertions.assertEquals("Lee", findUser.getName());
            Assertions.assertEquals("1234", findUser.getPassword());
        */