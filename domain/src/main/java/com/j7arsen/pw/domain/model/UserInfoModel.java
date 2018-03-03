package com.j7arsen.pw.domain.model;

import java.io.Serializable;

/**
 * Created by arsen on 28.02.2018.
 */

public class UserInfoModel implements Serializable {

    private long id;
    private String name;
    private String email;
    private long balance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
