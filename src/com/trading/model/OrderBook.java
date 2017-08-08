package com.trading.model;

public class OrderBook
{
    private int id;
    private String symbol;
    private int quanlity;
    private int type;
    private double price;
    
    public OrderBook ()
    {
        super ();
        // TODO Auto-generated constructor stub
    }

    public OrderBook (int id, String symbol, int quanlity, int type, double price)
    {
        super ();
        this.id = id;
        this.symbol = symbol;
        this.quanlity = quanlity;
        this.type = type;
        this.price = price;
    }

    
    
    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
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
