package com.hrskrs.commonssample.core;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hrskrs.commonslib.views.HKTextView;
import com.hrskrs.commonssample.R;
import com.hrskrs.commonssample.utils.FragmentTransactionUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hrskrs on 7/14/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

  private Unbinder unbinder;

  protected Toolbar toolbar;
  protected HKTextView toolbarTitle;
  protected ImageView toolbarLogo;

  @LayoutRes
  protected int getContentViewLayoutResId() {
    return R.layout.cs_activity_base;
  }

  @IdRes
  protected int getFragmentContainerId() {
    return R.id.cs_fragment_container;
  }

  protected abstract void setupToolbar(Toolbar toolbar);

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentViewLayoutResId());
    unbinder = ButterKnife.bind(this);
    initToolbar();
  }

  @Override
  protected void onDestroy() {
    if (unbinder != null) {
      unbinder.unbind();
    }
    super.onDestroy();
  }

  private void initToolbar() {
    toolbar = (Toolbar) findViewById(R.id.cs_toolbar);
    FrameLayout toolbarContainer = (FrameLayout) findViewById(R.id.cs_toolbar_container);
    toolbarLogo = (ImageView) findViewById(R.id.cs_toolbar_logo);
    toolbarTitle = (HKTextView) findViewById(R.id.cs_toolbar_title);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      toolbarContainer.setElevation(
          getResources().getDimension(R.dimen.cs_toolbar_elevation));
    }
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    setupToolbar(toolbar);
  }

  protected void addFragment(@NonNull Fragment fragment) {
    FragmentTransactionUtil.addFragment(getSupportFragmentManager(),
        fragment, getFragmentContainerId(), false);
  }

  protected void addFragment(@NonNull Fragment fragment, boolean addToBackStack) {
    FragmentTransactionUtil.addFragment(getSupportFragmentManager(),
        fragment, getFragmentContainerId(), addToBackStack);
  }

  protected void replaceFragment(@NonNull Fragment fragment) {
    FragmentTransactionUtil.replaceFragment(getSupportFragmentManager(),
        fragment, getFragmentContainerId(), false);
  }

  protected void replaceFragment(@NonNull Fragment fragment, boolean addToBackStack) {
    FragmentTransactionUtil.replaceFragment(getSupportFragmentManager(),
        fragment, getFragmentContainerId(), addToBackStack);
  }

  protected void showSnackbar(View view, String message) {
    Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
    snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
    snackbar.show();
  }

  protected Snackbar getSnackbar(View view, String message) {
    return Snackbar.make(view, message, Snackbar.LENGTH_LONG);
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    //prevent menu hardware button opening menu
    return keyCode == KeyEvent.KEYCODE_MENU || super.onKeyDown(keyCode, event);
  }
}
