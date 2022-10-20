package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class) //스프링 테스트를 사용하기 위한 설정
@ContextConfiguration(classes = UserDaoFactory.class) //설정 정보 불러오기

class UserDao3Test {

    @Autowired //의존관계 자동주입
    ApplicationContext context; //ApplicationContext 인터페이스 선언

    @Test
    void addAndGet() throws SQLException {
        UserDao3 userDao = context.getBean("awsUserDao", UserDao3.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        String id = "29";
        userDao.add(new User(id, "Lee", "1234"));
        User user = userDao.findById(id);
        assertEquals("DH", user.getName());
        assertEquals("DH", user.getPassword());
    }

    @Test
    //유저 데이터를 여러 개 넣어보고 삭제와 추가가 모두 정상 동작하는지 테스트
    void count() throws SQLException{
//        ApplicationContext context = new GenericXmlApplicationContext(
//                "applicationContext.xml");

        User user1 = new User("1", "Kim", "1123");
        User user2 = new User("2", "Cho", "3321");
        User user3 = new User("3", "Jang", "5532");

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