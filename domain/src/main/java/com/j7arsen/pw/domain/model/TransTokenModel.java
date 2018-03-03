package com.j7arsen.pw.domain.model;

import java.io.Serializable;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransTokenModel implements Serializable{

    private long id;
    private String date;
    private String username;
    private long amount;
    private long balance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
