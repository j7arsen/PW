package com.j7arsen.pw.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by arsen on 02.03.2018.
 */

public abstract class AbstractHolder <DATA> extends RecyclerView.ViewHolder {

    protected DATA model;
    protected IItemClickListener<DATA> clickListener;

    public AbstractHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind();


    public void setData(DATA model) {
        this.model = model;
        bind();
    }

    public void setOnItemClick(final IItemClickListener<DATA> clickListener) {
        itemView.setOnClickListener(view -> {
            if (clickListener != null) {
                this.clickListener = clickListener;
                clickListener.onItemClick(model);
            }
        });
    }

    public void setOnLongItemClick(final IItemLongClickListener<DATA> clickListener) {
        itemView.setOnLongClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemLongClick(model);
                return true;
            }
            return false;
        });
    }
}
