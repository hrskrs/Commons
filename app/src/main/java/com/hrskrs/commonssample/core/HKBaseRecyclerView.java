package com.hrskrs.commonssample.core;

import android.support.v7.widget.RecyclerView;

/**
 * Created by hrskrs on 8/12/2016.
 */
public abstract class HKBaseRecyclerView extends RecyclerView.Adapter<HKBaseViewHolder> {

  public abstract Object getItem(int position);

  @Override
  public void onBindViewHolder(HKBaseViewHolder holder, int position) {
    holder.populateItem(getItem(position));
  }
}
