package com.hrskrs.commonslib.views;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

/**
 * Created by hrskrs on 8/20/2016.
 */
public class HKCreditCardEditTextAnim extends TextInputLayout {

  private static final int NO_DEF_STYLE_ATTR = -1;
  private static final String NO_HINT = "";
  private HKCreditCardEditText hkCreditCardEditText;

  public HKCreditCardEditTextAnim(Context context) {
    super(context);
    init(context, null, NO_DEF_STYLE_ATTR);
  }

  public HKCreditCardEditTextAnim(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, NO_DEF_STYLE_ATTR);
  }

  public HKCreditCardEditTextAnim(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    if (attrs != null) {
      if (defStyleAttr != NO_DEF_STYLE_ATTR) {
        hkCreditCardEditText =
            new HKCreditCardEditText(context, attrs, defStyleAttr);
      } else {
        hkCreditCardEditText = new HKCreditCardEditText(context, attrs);
      }
    } else {
      hkCreditCardEditText = new HKCreditCardEditText(context);
    }
    hkCreditCardEditText.setHint(NO_HINT);

    addView(hkCreditCardEditText);
  }

  public HKCreditCardEditText getHkCreditCardEditText() {
    return hkCreditCardEditText;
  }

  public void setHkCreditCardEditText(HKCreditCardEditText hkCreditCardEditText) {
    this.hkCreditCardEditText = hkCreditCardEditText;
  }
}
