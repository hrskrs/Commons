package com.hrskrs.commonssample.ui.filterviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.hrskrs.commonssample.core.BaseActivity;

/**
 * Created by hrskrs on 8/19/2016.
 */
public class FilterViewsActivity extends BaseActivity {

  public static Intent newIntent(Context context){
    return new Intent(context, FilterViewsActivity.class);
  }

  @Override
  protected void setupToolbar(Toolbar toolbar) {
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(savedInstanceState == null){
      addFragment(FilterViewsFragment.newInstance());
    }
  }
}
