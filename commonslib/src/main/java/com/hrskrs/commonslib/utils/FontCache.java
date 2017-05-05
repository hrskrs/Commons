package com.hrskrs.commonslib.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by hrskrs on 6/23/2016.
 */
public class FontCache {

  private static HashMap<String, Typeface> fontCache = new HashMap<>();

  private FontCache(){}

  public static Typeface getTypeface(String fontName, Context context) {
    Typeface typeface = fontCache.get(fontName);

    if (typeface == null) {
      try {
        typeface = Typeface.createFromAsset(context.getAssets(), fontName);
      } catch (Exception e) {
        return null;
      }
      fontCache.put(fontName, typeface);
    }

    return typeface;
  }

}
