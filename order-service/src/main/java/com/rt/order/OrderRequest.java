package com.rt.order;

public class OrderRequest {
    private String orderId;
    private String symbol;
    private int qty;
    private double price;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
