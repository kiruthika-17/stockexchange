package com.model.stockexchange.entity;

import javax.persistence.*;

@Entity
@Table(name = "stock_exchange")
public class StockExchangeDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "symbol")
    private String symbol;

    @Column(name ="option_id")
    private Byte optionId;

    @Column(name ="price")
    private double price;

    @Column(name ="quantity")
    private Integer quantity;

    @Column(name ="status")
    private Byte status;

    public StockExchangeDetails(Integer id, Long userId, Byte option, double price, Integer quantity, Byte status) {
        this.id = id;
        this.userId = userId;
        this.optionId = option;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public StockExchangeDetails() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getOption() {
        return optionId;
    }

    public void setOption(Byte option) {
        this.optionId = option;
    }

    public double getPrice() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
