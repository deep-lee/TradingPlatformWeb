package com.trading.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.trading.dao.MakeTradeDao;
import com.trading.model.OrderBook;

public class MakeTradeService
{
    private MakeTradeDao makeTradeDao;
    public MakeTradeService ()
    {
        // TODO Auto-generated constructor stub
        makeTradeDao = new MakeTradeDao ();
    }
    
    public void makeTrade (HttpServletRequest request) throws NumberFormatException, ClassNotFoundException, SQLException
    {
        String symbol = request.getParameter ("symbol");
        String type = request.getParameter ("order_type");
        String buy_or_sale = request.getParameter ("buy_or_sale");
        String quantity = request.getParameter ("quantity");
        String price = request.getParameter ("price");
        String traderId = request.getParameter ("traderId");
        System.out.println (symbol + "\n" + type + "\n" + buy_or_sale + "\n" + quantity + "\n" + price + "\n" + traderId);
        
        if (type.equals ("Market Orders"))
        {
            makeMarketOrderTrade (symbol, buy_or_sale, quantity, price, Integer.parseInt (traderId));
        }
    }

    public void makeMarketOrderTrade (String symbol, String buy_or_sale,
        String quantity, String price, int traderId)
        throws ClassNotFoundException, SQLException
    {
        if (buy_or_sale.equals ("Buy"))
        {
            ArrayList<OrderBook> arrayList = makeTradeDao.getOrderBookWithSymbolAndType (symbol, 0, "desc");
            int orderQuantity = Integer.parseInt (quantity);
            HashMap<Integer, OrderBook> operations = new HashMap<> ();
            for (Iterator<OrderBook> iterator = arrayList.iterator (); iterator
                .hasNext ();)
            {
                OrderBook orderBook = (OrderBook) iterator.next ();
                if (orderBook.getQuanlity () < orderQuantity)
                {
                    operations.put (0, orderBook);
                    orderQuantity = orderQuantity - orderBook.getQuanlity ();
                }
                else if (orderBook.getQuanlity () == orderQuantity)
                {
                    operations.put (0, orderBook);
                    orderQuantity = 0;
                    break;
                }
                else
                {
                    operations.put (orderBook.getQuanlity () - orderQuantity,
                        orderBook);
                    orderQuantity = 0;
                    break;
                }
            }
            // 全部计算完
            if (orderQuantity == 0)
            {
                makeTradeDao.performOperations (operations, traderId, 1);
            }
            else
            { // 没有计算完
              // 全部Rejections
                makeTradeDao.rejectionAll (traderId, 0, Integer.parseInt (quantity), 1);
            }
        }
    }

    
}
