package com.trading.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.trading.model.Execution;
import com.trading.utils.MySQLConnUtils;

public class ExecutionDao
{
    public void insertExecution (Execution execution) throws ClassNotFoundException, SQLException
    {
        Connection connection = MySQLConnUtils.getMySQLConnection ();
        String sql = "insert into Execution(trader_id, status, quantity, price, type) values(?,?,?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement (sql);
        preparedStatement.setInt (1, execution.getTraderId ());
        preparedStatement.setInt (2, execution.getStatus ());
        preparedStatement.setInt (3, execution.getQuantity ());
        preparedStatement.setString (4, execution.getPrice () + "");
        preparedStatement.setInt (5, execution.getType ());
        
        preparedStatement.executeUpdate ();
        connection.close ();
    }
}
