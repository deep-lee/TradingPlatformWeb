package com.trading.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trading.service.MakeTradeService;

/**
 * Servlet implementation class MakeTradeServlet
 */
@WebServlet("/MakeTradeServlet")
public class MakeTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MakeTradeService makeTradeService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeTradeServlet() {
        super();
        // TODO Auto-generated constructor stub
        makeTradeService = new MakeTradeService ();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try
        {
            makeTradeService.makeTrade (request);
        }
        catch (NumberFormatException | ClassNotFoundException | SQLException e)
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
