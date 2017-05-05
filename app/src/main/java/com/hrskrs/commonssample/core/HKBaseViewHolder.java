package com.hrskrs.commonssample.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by hrskrs on 8/12/2016.
 */
public abstract class HKBaseViewHolder<T> extends RecyclerView.ViewHolder {

  public abstract void populateItem(T t);

  public HKBaseViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(itemView);
  }
}
