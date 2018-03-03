package com.j7arsen.pw.ui.transaction.list.holder;

import android.view.View;
import android.widget.TextView;

import com.j7arsen.pw.R;
import com.j7arsen.pw.base.adapter.AbstractHolder;
import com.j7arsen.pw.domain.model.TransTokenModel;
import com.j7arsen.pw.utils.FormatterDate;

import butterknife.BindView;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransactionHolder extends AbstractHolder<TransTokenModel> {

    @BindView(R.id.tv_transaction_corespondent)
    TextView tvTransactionCorespondent;
    @BindView(R.id.tv_transaction_date)
    TextView tvTransactionDate;
    @BindView(R.id.tv_transaction_amount)
    TextView tvTransactionAmount;
    @BindView(R.id.tv_transaction_balance)
    TextView tvTransactionBalance;

    public TransactionHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind() {
        tvTransactionCorespondent.setText(model.getUsername());
        tvTransactionDate.setText(FormatterDate.formatServerDate(model.getDate()));
        tvTransactionAmount.setText(String.valueOf(model.getAmount()));
        tvTransactionBalance.setText(String.valueOf(model.getBalance()));
    }
}