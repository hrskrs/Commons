package com.hrskrs.commonssample.ui.fonts;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.hrskrs.commonssample.R;
import com.hrskrs.commonssample.core.BaseFragment;

/**
 * Created by hrskrs on 7/16/2016.
 */
public class EdittextFontsFragment extends BaseFragment {

  public static EdittextFontsFragment newInstance() {
    return new EdittextFontsFragment();
  }

  @Override
  protected int getContentLayoutResId() {
    return R.layout.fragment_edittext_fonts;
  }

  @Override
  protected void setupToolbar(Toolbar toolbar) {

  }

  @Override
  protected void populateUI(LayoutInflater inflater, View rootView) {
  }
}
