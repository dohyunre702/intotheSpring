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
            User user = null;
            // rs.next(); null;
            if (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("name"), rs.getString("name"));
            }
            rs.close();
            pstmt.close();
            c.close();

            //데이터 생길 때 생기는 예외 처리하기
            if(user==null) throw new EmptyResultDataAccessException(1);
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //삭제
    public void deleteAll() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = cm.makeConnection();
            ps = c.prepareStatement("DELETE FROM users"); //쿼리 입력
            rs = ps.executeQuery();
            rs.next(); //쿼리 업데이트
            return rs.getInt(1);

            //c, ps, rs가 null일 때 리소스 반환이 불가해 서버가 down되는 치명적 문제 발생
            //connection, preparedStatement에서 에러가 나도 ps.close() c.close()를 실행하기 위한 처리
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {//errror가 나도 실행되는 블럭
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }
                if (c != null) {
                    try {
                        c.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
    }

        //쿼리 count
        public int getCount() {
            Map<String, String> env = System.getenv(); //없어도 됨?
            try {
                Connection conn = cm.makeConnection();

                PreparedStatement psCnt = conn.prepareStatement("SELECT COUNT(*) FROM users;");

                //쿼리 입력
                ResultSet rs = psCnt.executeQuery();
                System.out.println("DB Get Count");
                rs.next();
                int count = rs.getInt(1);

                rs.close();
                psCnt.close();
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