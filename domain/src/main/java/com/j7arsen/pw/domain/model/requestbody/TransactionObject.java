package com.j7arsen.pw.domain.model.requestbody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransactionObject {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private String amount;

    public TransactionObject(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
