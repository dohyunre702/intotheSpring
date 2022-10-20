package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao3 {

    private ConnectionMaker cm;

    public UserDao3() {
        this.cm = new AwsConnectionMaker();
    }

    public UserDao3(ConnectionMaker cm) {
        this.cm = cm;
    }


    //insert문
    public void add(User user)  {
        Map<String, String> env = System.getenv();
        try {

            //db 접속
            Connection conn = cm.makeConnection();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?)");
            //query문 작성
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
            //query문 실행
                int status = ps.executeUpdate();

                ps.close();
                conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        //connection separate
        Map<String, String> env = System.getenv();
        try {
            //DB접속
            Connection c = cm.makeConnection();

            //query문 작성
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);

            //query문 실행
            ResultSet rs = pstmt.executeQuery(); //ResultSet?
            rs.next();

            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("name"));

            rs.close();
            pstmt.close();
            c.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //삭제
    public void deleteAll() {
        Map<String, String> env = System.getenv();
        try {
            Connection conn = cm.makeConnection();

            //쿼리 입력
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users");

            //쿼리 실행
            ps.executeUpdate();
            System.out.println("DB DeleteAll 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        //쿼리 count
        public int getCount () {
            Map<String, String> env = System.getenv();
            try {
                Connection conn = cm.makeConnection();

                PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM users;");

                //쿼리 입력
                ResultSet rs = ps.executeQuery();
                System.out.println("DB Get Count");
                rs.next();
                int count = rs.getInt(1);

                rs.close();
                ps.close();
                conn.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

            public static void main (String[]args){
                UserDao3 userDao = new UserDao3();
                //userDao.add(new User("7", "Ruru", "1234"));
                User user = userDao.findById("4");
                System.out.println(user.getName());
            }

        }