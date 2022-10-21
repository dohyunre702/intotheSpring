package com.likelion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    //인터페이스에 의존하게 해서, 필요에 따라 구현체를 선택할 수 있는 디자인 패턴
    //PreparedStatement를 리턴
    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;



}
