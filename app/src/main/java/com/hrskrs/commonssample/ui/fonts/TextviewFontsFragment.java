package com.hrskrs.commonssample.ui.fonts;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.hrskrs.commonssample.R;
import com.hrskrs.commonssample.core.BaseFragment;

/**
 * Created by hrskrs on 7/14/2016.
 */
public class TextviewFontsFragment extends BaseFragment {

  public static TextviewFontsFragment newInstance(){
    return new TextviewFontsFragment();
  }

  @Override
  protected int getContentLayoutResId() {
    return R.layout.fragment_textview_fonts;
  }

  @Override
  protected void setupToolbar(Toolbar toolbar) {
    toolbar.setTitle("Custom Fonts");
    toolbar.setNavigationIcon(R.drawable.ic_back);
  }

  @Override
  protected void populateUI(LayoutInflater inflater, View rootView) {
  }

}
