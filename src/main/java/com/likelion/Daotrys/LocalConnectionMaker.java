package com.likelion.Daotrys;

import com.likelion.dao.ConnectionMaker;

import java.sql.Connection;
import java.sql.SQLException;

//인터페이스, 의존용
public class LocalConnectionMaker implements ConnectionMaker {

    @Override
    public Connection makeConnection() throws SQLException {
        return null;
    }
}
