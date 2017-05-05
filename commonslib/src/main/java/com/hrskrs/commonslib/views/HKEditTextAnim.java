package com.hrskrs.commonslib.views;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.util.AttributeSet;

/**
 * Created by hrskrs on 8/12/2016.
 */
public class HKEditTextAnim extends TextInputLayout {

  private static final int NO_DEF_STYLE_ATTR = -1;
  private static final String NO_HINT = "";
  private HKEditText hkEditText;

  public HKEditTextAnim(Context context) {
    super(context);
    init(context, null, NO_DEF_STYLE_ATTR);
  }

  public HKEditTextAnim(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, NO_DEF_STYLE_ATTR);
  }

  public HKEditTextAnim(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    if (attrs != null) {
      if (defStyleAttr != NO_DEF_STYLE_ATTR) {
        hkEditText = new HKEditText(context, attrs, defStyleAttr);
      } else {
        hkEditText = new HKEditText(context, attrs);
      }
    } else {
      hkEditText = new HKEditText(context);
    }
    hkEditText.setHint(NO_HINT);

    addView(hkEditText);
  }

  public HKEditText getHkEditText() {
    return hkEditText;
  }

  public void setHkEditText(HKEditText hkEditText) {
    this.hkEditText = hkEditText;
  }

  public Editable getText() {
    return getHkEditText().getText();
  }

  public void setText(CharSequence value) {
    getHkEditText().setText(value);
  }
}
