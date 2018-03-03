package com.j7arsen.pw.domain.model.requestbody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arsen on 01.03.2018.
 */

public class RecipientFilter {

    @SerializedName("filter")
    @Expose
    private String filter;

    public RecipientFilter(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
