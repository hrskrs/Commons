package com.hrskrs.commonssample.ui.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.hrskrs.commonssample.core.BaseActivity;

public class MainActivity extends BaseActivity {

  @Override
  protected void setupToolbar(Toolbar toolbar) {
    toolbarTitle.setText("CommonsLib Samples");
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(savedInstanceState == null){
      addFragment(MainFragment.newInstance());
    }
  }
}
