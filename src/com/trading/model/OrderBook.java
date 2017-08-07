package com.trading.model;

public class OrderBook
{
    private String symbol;
    private int quanlity;
    private int type;
    private double price;
    
    public OrderBook ()
    {
        super ();
        // TODO Auto-generated constructor stub
    }

    public OrderBook (String symbol, int quanlity, int type, double price)
    {
        super ();
        this.symbol = symbol;
        this.quanlity = quanlity;
        this.type = type;
        this.price = price;
    }

    public String getSymbol ()
    {
        return symbol;
    }

    public void setSymbol (String symbol)
    {
        this.symbol = symbol;
    }

    public int getQuanlity ()
    {
        return quanlity;
    }

    public void setQuanlity (int quanlity)
    {
        this.quanlity = quanlity;
    }

    public int getType ()
    {
        return type;
    }

    public void setType (int type)
    {
        this.type = type;
    }

    public double getPrice ()
    {
        return price;
    }

    public void setPrice (double price)
    {
        this.price = price;
    }

    
}
