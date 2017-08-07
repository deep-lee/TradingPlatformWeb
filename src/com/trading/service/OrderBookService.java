package com.trading.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.print.DocFlavor.STRING;

import com.google.gson.Gson;
import com.trading.model.OrderBook;
import com.trading.utils.MySQLConnUtils;

public class OrderBookService
{
    public static boolean checkSymbolExist(String symbol) throws ClassNotFoundException, SQLException {
        Connection connection = MySQLConnUtils.getMySQLConnection ();
        String sql = "select * from OrderBook where symbol = ?";
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        preparedStatement.setString (1, symbol);
        ResultSet rs = preparedStatement.executeQuery ();
        rs.last (); //指针移到最后一行 
        if (rs.getRow () == 0)
        {
            return false;
        } else {
            return true;
        }
    }
    
    public static HashMap<String, ArrayList<OrderBook>> getAllSymbolOrders (String symbol) throws ClassNotFoundException, SQLException
    {
        ArrayList<OrderBook> returnDataType1 = new ArrayList<> ();
        ArrayList<OrderBook> returnDataType0 = new ArrayList<> ();
        Connection connection = MySQLConnUtils.getMySQLConnection ();
        String sql1 = "select * from OrderBook where symbol = ? and type=1 order by price";
        PreparedStatement preparedStatement1 = connection.prepareStatement (sql1);
        preparedStatement1.setString (1, symbol);
        ResultSet rs1 = preparedStatement1.executeQuery ();
        while (rs1.next()){
            OrderBook tradeBook = new OrderBook ();
            tradeBook.setSymbol (rs1.getString (1));
            tradeBook.setQuanlity (rs1.getInt (2));
            tradeBook.setType (rs1.getInt (3));
            tradeBook.setPrice (rs1.getDouble (4));
            returnDataType1.add (tradeBook);
        }
        
        System.out.println (returnDataType1);
        
        String sql0 = "select * from OrderBook where symbol = ? and type=0 order by price desc";
        PreparedStatement preparedStatement0 = connection.prepareStatement (sql0);
        preparedStatement0.setString (1, symbol);
        ResultSet rs0 = preparedStatement0.executeQuery ();
        while (rs0.next()){
            OrderBook tradeBook = new OrderBook ();
            tradeBook.setSymbol (rs0.getString (1));
            tradeBook.setQuanlity (rs0.getInt (2));
            tradeBook.setType (rs0.getInt (3));
            tradeBook.setPrice (rs0.getDouble (4));
            returnDataType0.add (tradeBook);
        }
        System.out.println (returnDataType0);
        
        rs1.close();
        preparedStatement1.close();
        rs0.close();
        preparedStatement0.close();
        connection.close();
        HashMap<String, ArrayList<OrderBook>> result = new HashMap<> ();
        result.put ("bid", returnDataType1);
        result.put ("ask", returnDataType0);
        
        return result;
    }
}
