package com.trading.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.trading.model.Execution;
import com.trading.model.OrderBook;
import com.trading.utils.MySQLConnUtils;

public class MakeTradeDao
{
    private ExecutionDao executionDao;
    public MakeTradeDao ()
    {
        // TODO Auto-generated constructor stub
        executionDao = new ExecutionDao ();
    }
    public ArrayList<OrderBook> getOrderBookWithSymbolAndType (String symbol, int type, String orderby) throws ClassNotFoundException, SQLException
    {
        Connection connection = MySQLConnUtils.getMySQLConnection ();
        ArrayList<OrderBook> arrayList = new ArrayList<> ();
        String sql = "select * from OrderBook where symbol= ? and type=? order by price " + orderby;
        PreparedStatement preparedStatement = connection
            .prepareStatement (sql);
        preparedStatement.setString (1, symbol);
        preparedStatement.setInt (2, type);
        ResultSet rs = preparedStatement.executeQuery ();
        while (rs.next ())
        {
            OrderBook orderBook = new OrderBook ();
            orderBook.setId (rs.getInt (1));
            orderBook.setSymbol (rs.getString (2));
            orderBook.setQuanlity (rs.getInt (3));
            orderBook.setType (rs.getInt (4));
            orderBook.setPrice (Double.parseDouble (rs.getString (5)));
            arrayList.add (orderBook);
        }
        
        rs.close ();
        connection.close ();
        return arrayList;
    }
    
    public void performOperations (HashMap<Integer, OrderBook> operations, int traderId, int type)
        throws ClassNotFoundException, SQLException
    {
        for (Map.Entry<Integer, OrderBook> entry : operations.entrySet ())
        {
            int quantity = entry.getKey ();
            OrderBook orderBook = entry.getValue ();
            if (quantity == 0)
            {
                removeOrderBook (orderBook);
                makeExecution (orderBook, orderBook.getQuanlity (), traderId, 1, type);
            }
            else
            {
                updateOrderBook (orderBook, quantity);
                makeExecution (orderBook, orderBook.getQuanlity () - quantity, traderId, 1, type);
            }
        }
    }
    
    public void makeExecution(OrderBook orderBook, int quantity, int traderId, int status, int type) throws ClassNotFoundException, SQLException {
        Execution execution = new Execution ();
        execution.setTraderId (traderId);
        execution.setStatus (status);
        execution.setQuantity (quantity);
        execution.setPrice (orderBook.getPrice ());
        execution.setType (type);
        
        executionDao.insertExecution (execution);
    }
    
    public void removeOrderBook (OrderBook orderBook)
        throws ClassNotFoundException, SQLException
    {
        Connection connection = MySQLConnUtils.getMySQLConnection ();
        String sql = "delete from OrderBook where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        preparedStatement.setInt (1, orderBook.getId ());
        preparedStatement.executeUpdate ();
        connection.close ();
    }

    public void updateOrderBook (OrderBook orderBook, int quantity)
        throws ClassNotFoundException, SQLException
    {
        Connection connection = MySQLConnUtils.getMySQLConnection ();
        String sql = "update OrderBook set quantity = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        preparedStatement.setInt (1, orderBook.getQuanlity () - quantity);
        preparedStatement.setInt (2, orderBook.getId ());
        preparedStatement.executeUpdate ();
        connection.close ();
    }
    
    public void rejectionAll (int traderId, int status, int quantity, int type) throws ClassNotFoundException, SQLException {
        Execution execution = new Execution ();
        execution.setTraderId (traderId);
        execution.setStatus (status);
        execution.setQuantity (quantity);
        execution.setPrice (0);
        execution.setType (type);
        executionDao.insertExecution (execution);
    }
}
