package com.trading.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trading.model.Trader;
import com.trading.utils.MySQLConnUtils;

public class LoginService
{
    public static boolean checkTraderLogin (String username, String password)
        throws ClassNotFoundException, SQLException
    {
        Connection connection = MySQLConnUtils.getMySQLConnection ();
        String sql = "Select * from Trader t where t.name = ? and t.password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        preparedStatement.setString (1, username);
        preparedStatement.setString (2, password);

        ResultSet rs = preparedStatement.executeQuery ();
        rs.last (); //指针移到最后一行 
        if (rs.getRow () == 0)
        {
            rs.close();
            preparedStatement.close();
            connection.close();
            return false;
        } else {
            rs.close();
            preparedStatement.close();
            connection.close();
            return true;
        }
        
    }
    
    public static Trader getTraderByNameAndPass(String username, String password) throws ClassNotFoundException, SQLException {
        Connection connection = MySQLConnUtils.getMySQLConnection ();
        String sql = "Select * from Trader t where t.name = ? and t.password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        preparedStatement.setString (1, username);
        preparedStatement.setString (2, password);

        ResultSet rs = preparedStatement.executeQuery ();
        while (rs.next()){
            Trader trader = new Trader ();
            trader.setId (rs.getInt (1));
            trader.setName (rs.getString (2));
            trader.setPassword (rs.getString ("password"));
            trader.setEmail (rs.getString ("email"));
            return trader;
        }
        return null;
    }
}
