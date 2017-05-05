package com.hrskrs.commonssample.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hrskrs.commonssample.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hrskrs on 7/14/2016.
 */
public abstract class BaseFragment extends Fragment {

  private Unbinder unbinder;

  @LayoutRes
  protected abstract int getContentLayoutResId();

  protected abstract void setupToolbar(Toolbar toolbar);

  protected abstract void populateUI(LayoutInflater inflater, View rootView);

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(getContentLayoutResId(), container, false);
    unbinder = ButterKnife.bind(this, rootView);
    Toolbar toolbar = ((BaseActivity)getActivity()).toolbar;
    setupToolbar(toolbar);
    populateUI(inflater, rootView);
    return rootView;
  }

  @Override
  public void onDestroyView() {
    if (unbinder != null) {
      unbinder.unbind();
    }
    super.onDestroyView();
  }

  protected void showSnackbar(View view, String message) {
    Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
    snackbar.setActionTextColor(
        getActivity().getResources().getColor(R.color.colorAccent));
    snackbar.show();
  }

  protected Snackbar getSnackbar(View view, String message) {
    return Snackbar.make(view, message, Snackbar.LENGTH_LONG);
  }
}
