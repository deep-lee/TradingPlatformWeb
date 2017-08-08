package com.trading.model;

public class Execution
{
    private int id;
    private int traderId;
    private int status;
    private int quantity;
    private double price;
    private int type;
    private String createtime;
    public Execution ()
    {
        super ();
        // TODO Auto-generated constructor stub
    }
    public Execution (int id, int traderId, int status, int quantity,
        double price, int type, String createtime)
    {
        super ();
        this.id = id;
        this.traderId = traderId;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.createtime = createtime;
    }
    public int getId ()
    {
        return id;
    }
    public void setId (int id)
    {
        this.id = id;
    }
    public int getTraderId ()
    {
        return traderId;
    }
    public void setTraderId (int traderId)
    {
        this.traderId = traderId;
    }
    public int getStatus ()
    {
        return status;
    }
    public void setStatus (int status)
    {
        this.status = status;
    }
    public int getQuantity ()
    {
        return quantity;
    }
    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }
    public double getPrice ()
    {
        return price;
    }
    public void setPrice (double price)
    {
        this.price = price;
    }
    public int getType ()
    {
        return type;
    }
    public void setType (int type)
    {
        this.type = type;
    }
    public String getCreatetime ()
    {
        return createtime;
    }
    public void setCreatetime (String createtime)
    {
        this.createtime = createtime;
    }
    
    
}
