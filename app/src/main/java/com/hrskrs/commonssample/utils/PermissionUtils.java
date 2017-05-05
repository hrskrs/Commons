package com.hrskrs.commonssample.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by hrskrs on 3/9/2017.
 */

public class PermissionUtils {

  public static final int REQUEST_CODE_ACTION_MANAGE_OVERLAY = 898;

  private Context context;

  private PermissionUtils(){
    throw new AssertionError();
  }

  public PermissionUtils(Context context) {
    this.context = context;
  }

  @TargetApi(Build.VERSION_CODES.M)
  public boolean canDrawOverlays() {
    if(isMarshmallowOrHigher()) {
      return Settings.canDrawOverlays(context);
    }
    return true;
  }

  private boolean isMarshmallowOrHigher() {
    return android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
  }
}
