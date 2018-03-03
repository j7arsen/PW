package com.j7arsen.pw.ui.transaction.list.adapter;

import android.view.ViewGroup;

import com.j7arsen.pw.R;
import com.j7arsen.pw.base.adapter.AbstractRecyclerViewAdapter;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.ui.transaction.list.holder.TransactionHolder;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransactionAdapter extends AbstractRecyclerViewAdapter<TransTokenModel, TransactionHolder> {

    public TransactionAdapter() {
        super(R.layout.item_transaction);
    }

    @Override
    public TransactionHolder onInitViewHolder(ViewGroup parent) {
        return new TransactionHolder(inflater.inflate(resource, parent, false));
    }
}
