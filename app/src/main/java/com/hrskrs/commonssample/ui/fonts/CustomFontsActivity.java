package com.hrskrs.commonssample.ui.fonts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.hrskrs.commonssample.R;
import com.hrskrs.commonssample.core.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrskrs on 7/15/2016.
 */
public class CustomFontsActivity extends BaseActivity {

  private ViewPager viewPager;
  private TabLayout tabLayout;

  public static Intent newIntent(Context context){
    return new Intent(context, CustomFontsActivity.class);
  }

  @Override
  protected int getContentViewLayoutResId() {
    return R.layout.activity_custom_fonts;
  }

  @Override
  protected void setupToolbar(Toolbar toolbar) {
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewPager = (ViewPager) findViewById(R.id.custom_fonts_viewpager);
    setupViewPager(viewPager);
    tabLayout = (TabLayout) findViewById(R.id.custom_fonts_tablayout);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupViewPager(ViewPager viewPager){
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(TextviewFontsFragment.newInstance(), "HK-TextViews");
    adapter.addFragment(EdittextFontsFragment.newInstance(), "HK-EditTexts");
    adapter.addFragment(EdittextAnimFontsFragment.newInstance(), "HK-EditTexts-Anim");

    viewPager.setAdapter(adapter);
  }

  class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> fragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return fragmentList.get(position);
    }

    @Override
    public int getCount() {
      return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return fragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
      fragmentList.add(fragment);
      fragmentTitleList.add(title);
    }
  }

}
