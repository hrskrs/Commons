package com.hrskrs.commonssample.ui.fonts;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.hrskrs.commonslib.views.HKEditTextAnim;
import com.hrskrs.commonssample.R;
import com.hrskrs.commonssample.core.BaseFragment;

/**
 * Created by hrskrs on 7/16/2016.
 */
public class EdittextAnimFontsFragment extends BaseFragment {

  public static EdittextAnimFontsFragment newInstance() {
    return new EdittextAnimFontsFragment();
  }

  @Override
  protected int getContentLayoutResId() {
    return R.layout.fragment_edittext_anim_fonts;
  }

  @Override
  protected void setupToolbar(Toolbar toolbar) {
  }

  @Override
  protected void populateUI(LayoutInflater inflater, View rootView) {
    HKEditTextAnim errorMsg = (HKEditTextAnim) rootView.findViewById(R.id.input_layout);
    errorMsg.setError("Error message can be shown here!");
  }
}
