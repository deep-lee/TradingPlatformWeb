package com.trading.model;

public class Trade
{
    private int id;
    private int traderId;
    private Double price;
    private String equityId;
    private boolean bnuy;
    private int amount;
    private String createtime;
    public Trade ()
    {
        super ();
        // TODO Auto-generated constructor stub
    }
    public Trade (int id, int traderId, Double price, String equityId,
        boolean bnuy, int amount, String createtime)
    {
        super ();
        this.id = id;
        this.traderId = traderId;
        this.price = price;
        this.equityId = equityId;
        this.bnuy = bnuy;
        this.amount = amount;
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
    public Double getPrice ()
    {
        return price;
    }
    public void setPrice (Double price)
    {
        this.price = price;
    }
    public String getEquityId ()
    {
        return equityId;
    }
    public void setEquityId (String equityId)
    {
        this.equityId = equityId;
    }
    public boolean isBnuy ()
    {
        return bnuy;
    }
    public void setBnuy (boolean bnuy)
    {
        this.bnuy = bnuy;
    }
    public int getAmount ()
    {
        return amount;
    }
    public void setAmount (int amount)
    {
        this.amount = amount;
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

