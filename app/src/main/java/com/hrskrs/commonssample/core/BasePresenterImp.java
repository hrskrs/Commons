package com.hrskrs.commonssample.core;

import java.lang.ref.WeakReference;

/**
 * Created by hrskrs on 2/24/2017.
 */

public class BasePresenterImp implements BasePresenter {

  private WeakReference<BaseView> baseViewWeakReference;

  public BasePresenterImp(BaseView view) {
    baseViewWeakReference = new WeakReference<>(view);
  }

}
