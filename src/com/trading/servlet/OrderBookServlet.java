package com.trading.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.trading.model.OrderBook;
import com.trading.service.OrderBookService;

/**
 * Servlet implementation class OrderBookServlet
 */
@WebServlet("/OrderBookServlet")
public class OrderBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	    String symbol = request.getParameter ("symbol");
	    boolean exists;
        try
        {
            exists = OrderBookService.checkSymbolExist (symbol);
            if (exists)
            {
                HashMap<String, ArrayList<OrderBook>> result = OrderBookService.getAllSymbolOrders (symbol);
                // response.getWriter ().append ("{exists: true, data:").append (result).append ("}");
                HashMap<String, Object> responseResult = new HashMap<> ();
                responseResult.put ("exists", true);
                responseResult.put ("data", result);
                response.getWriter ().append (new Gson ().toJson (responseResult));
            } else {
                HashMap<String, Object> responseResult = new HashMap<> ();
                responseResult.put ("exists", false);
                response.getWriter().append (new Gson ().toJson (responseResult));
            }
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
