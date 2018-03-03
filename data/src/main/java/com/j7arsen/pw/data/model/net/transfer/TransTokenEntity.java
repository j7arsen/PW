package com.j7arsen.pw.data.model.net.transfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransTokenEntity {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("amount")
    @Expose
    private long amount;
    @SerializedName("balance")
    @Expose
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
