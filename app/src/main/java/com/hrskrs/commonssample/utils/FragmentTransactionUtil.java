package com.hrskrs.commonssample.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by hrskrs on 7/14/2016.
 */
public class FragmentTransactionUtil {

  private FragmentTransactionUtil(){}

  public static void addFragment(FragmentManager fragmentManager,
                                 @NonNull Fragment fragment,
                                 @IdRes int fragmentContainerId,
                                 boolean addToBackStack) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
//    transaction.setCustomAnimations(R.anim.enter,
//        R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
    transaction.add(fragmentContainerId, fragment);
    if(addToBackStack) {
      transaction.addToBackStack(fragment.getClass().getSimpleName());
    }
    transaction.commit();
  }

  public static void replaceFragment(FragmentManager fragmentManager,
                                 @NonNull Fragment fragment,
                                 @IdRes int fragmentContainerId,
                                 boolean addToBackStack) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
//    transaction.setCustomAnimations(R.anim.enter,
//        R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
    transaction.replace(fragmentContainerId, fragment);
    if(addToBackStack) {
      transaction.addToBackStack(fragment.getClass().getSimpleName());
    }
    transaction.commit();
  }
}
