package com.hrskrs.commonssample.ui.filterviews;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.hrskrs.commonssample.R;
import com.hrskrs.commonssample.core.BaseFragment;

/**
 * Created by hrskrs on 8/19/2016.
 */
public class FilterViewsFragment extends BaseFragment {

  public static FilterViewsFragment newInstance() {
    return new FilterViewsFragment();
  }

  @Override
  protected int getContentLayoutResId() {
    return R.layout.fragment_filter_views;
  }

  @Override
  protected void setupToolbar(Toolbar toolbar) {
    toolbar.setTitle("Filter Views");
    toolbar.setNavigationIcon(R.drawable.ic_back);
  }

  @Override
  protected void populateUI(LayoutInflater inflater, View rootView) {
  }
}
