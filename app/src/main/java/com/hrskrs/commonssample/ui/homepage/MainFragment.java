package com.hrskrs.commonssample.ui.homepage;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.hrskrs.commonssample.R;
import com.hrskrs.commonssample.core.BaseFragment;
import com.hrskrs.commonssample.ui.filterviews.FilterViewsActivity;
import com.hrskrs.commonssample.ui.fonts.CustomFontsActivity;

import butterknife.OnClick;

/**
 * Created by hrskrs on 7/15/2016.
 */
public class MainFragment extends BaseFragment {

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override
  protected int getContentLayoutResId() {
    return R.layout.fragment_main;
  }

  @Override
  protected void setupToolbar(Toolbar toolbar) {
    toolbar.setNavigationIcon(R.drawable.ic_menu);
  }

  @Override
  protected void populateUI(LayoutInflater inflater, View rootView) {
  }

  @OnClick({
      R.id.custom_fonts_button,
      R.id.filter_views_button
  })
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.custom_fonts_button:
        startActivity(CustomFontsActivity.newIntent(getActivity()));
        break;
      case R.id.filter_views_button:
        startActivity(FilterViewsActivity.newIntent(getActivity()));
        break;
      default:
        break;
    }
  }
}
