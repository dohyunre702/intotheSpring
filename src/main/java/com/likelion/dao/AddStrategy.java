package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStrategy implements StatementStrategy{
    // user 생성자 추가
    User user;

    public AddStrategy(User user) {
        this.user = user;
    }

    //Addstrategy 도입
    @Override
    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
        //return connection.prepareStatement("DELETE FROM users");
        PreparedStatement ps = ps.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?)");
        //query문 작성
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        return null;
    }
}
