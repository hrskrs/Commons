package com.hrskrs.commonslib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;

import com.hrskrs.commonslib.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hrskrs on 8/19/2016.
 * Credit Card regular expressions are based on http://www.regular-expressions.info/creditcard.html
 */
public class HKCreditCardEditText extends HKEditText {

  private static final int NO_CC = 0;

  private static final String VISA_CARD_PATTERN = "^4[0-9]{2,12}(?:[0-9]{3})?$";
  private static final String MASTER_CARD_PATTERN = "^5[1-5][0-9]{1,14}$";
  private static final String AMERICAN_EXPRESS_PATTERN = "^3[47][0-9]{1,13}$";

  private int defaultDrawableResId;
  private int currentDrawableResId = NO_CC;
  private Drawable currentDrawable;

  private SparseArray<Pattern> creditCardPatterns;

  private CreditCardFormatWatcher creditCardFormatWatcher;
  private int ccType = 0;

  public HKCreditCardEditText(Context context) {
    super(context);
    init(context, null);
  }

  public HKCreditCardEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public HKCreditCardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    creditCardFormatWatcher = getCreditCardFormatWatcher();
    if (attrs != null) {
      TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HKViewCreditCardAttrs);
      if (ta != null) {
        if (ta.hasValue(R.styleable.HKViewCreditCardAttrs_hkType)) {
          ccType = ta.getInt(R.styleable.HKViewCreditCardAttrs_hkType, 0);
          creditCardFormatWatcher.setCcType(ccType);
        }
        String dividerString = ta.getString(R.styleable.HKViewCreditCardAttrs_hkDivider);
        if (!TextUtils.isEmpty(dividerString)) {
          //Get only first char even if string contains other chars
          char divider = dividerString.charAt(0);
          creditCardFormatWatcher.setDivider(divider);
        }
        ta.recycle();
      }
    }

    addTextChangedListener(creditCardFormatWatcher);
    //Allow only numbers and divider to be typed
    setKeyListener(DigitsKeyListener.getInstance(
        "123456789" + creditCardFormatWatcher.getDivider()));
    setSingleLine(true);

    //Show image only if Credit card
    if (ccType == 0) {
      //Init image indicator
      initCreditCardImageIndicator();
    }
  }

  private void initCreditCardImageIndicator() {
    defaultDrawableResId = R.drawable.ic_cc_colorless;
    if (creditCardPatterns == null) {
      creditCardPatterns = new SparseArray<>();
      creditCardPatterns.put(R.drawable.ic_cc_visa, Pattern.compile(VISA_CARD_PATTERN));
      creditCardPatterns.put(R.drawable.ic_cc_mastercard, Pattern.compile(MASTER_CARD_PATTERN));
      creditCardPatterns.put(R.drawable.ic_cc_amex, Pattern.compile(AMERICAN_EXPRESS_PATTERN));
    }
  }

  @Override
  protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
    if (ccType == 0) {
      setCreditCardImageIndicator(text);
    }
  }

  private void setCreditCardImageIndicator(CharSequence text) {
    if (ccType == 0) {
      if (creditCardPatterns == null) {
        initCreditCardImageIndicator();
      }

      int drawableResId = NO_CC;
      int patternsListSize = creditCardPatterns.size();
      for (int i = NO_CC; i < patternsListSize; i++) {
        int key = creditCardPatterns.keyAt(i);
        Pattern pattern = creditCardPatterns.get(key);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
          drawableResId = key;
          break;
        }
      }

      if (drawableResId > NO_CC && drawableResId != currentDrawableResId) {
        currentDrawableResId = drawableResId;
      } else if (drawableResId == NO_CC) {
        currentDrawableResId = defaultDrawableResId;
      }

      currentDrawable = ResourcesCompat.getDrawable(getResources(), currentDrawableResId, null);
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (ccType == 0) {
      if (currentDrawable == null) {
        return;
      }
      //If an error is displayed we subtract 32 DIP offset from our right coordinate
      //Otherwise we wont be able to see the error icon as we draw our image over
      //the area where the icon is displayed
      int rightOffset = 0;
      CharSequence error = getError();
      if (error != null && error.length() > 0) {
        rightOffset = (int) getResources().getDisplayMetrics().density * 32;
      }

      int top = getPaddingTop();
      int right = getWidth() - getPaddingRight() - rightOffset;
      int bottom = getHeight() - getPaddingBottom();
      float ratio = (float) currentDrawable.getIntrinsicWidth() / (float) currentDrawable.getIntrinsicHeight();
      int left = (int) (right - ((bottom - top) * ratio));
      //Scale image depending on height available
      currentDrawable.setBounds(left, top, right, bottom);

      currentDrawable.draw(canvas);
    }
  }

  private CreditCardFormatWatcher getCreditCardFormatWatcher() {
    if (creditCardFormatWatcher == null) {
      creditCardFormatWatcher = new CreditCardFormatWatcher();
    }
    return creditCardFormatWatcher;
  }

  public void setDivider(char divider) {
    getCreditCardFormatWatcher().setDivider(divider);
  }

  public void setCcType(int ccType) {
    getCreditCardFormatWatcher().setCcType(ccType);
  }

  public int getCcType() {
    return getCreditCardFormatWatcher().getCcType();
  }

  public char getDivider() {
    return getCreditCardFormatWatcher().getDivider();
  }

  class CreditCardFormatWatcher implements TextWatcher {

    private char divider = '/';

    private static final int CARD_NUMBER_TOTAL_SYMBOLS = 19;
    private static final int CARD_NUMBER_TOTAL_DIGITS = 16;
    private static final int CARD_NUMBER_DIVIDER_MODULO = 5;
    private static final int CARD_NUMBER_DIVIDER_POSITION = CARD_NUMBER_DIVIDER_MODULO - 1;

    private static final int CARD_DATE_TOTAL_SYMBOLS = 5;
    private static final int CARD_DATE_TOTAL_DIGITS = 4;
    private static final int CARD_DATE_DIVIDER_MODULO = 3;
    private static final int CARD_DATE_DIVIDER_POSITION = CARD_DATE_DIVIDER_MODULO - 1;

    private static final int CARD_CVC_TOTAL_SYMBOLS = 3;

    private int ccType = 0;

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
      switch (ccType) {
        //CC NO
        case 0:
          if (!isInputCorrect(s,
              CARD_NUMBER_TOTAL_SYMBOLS,
              CARD_NUMBER_DIVIDER_MODULO,
              divider)) {
            s.replace(0, s.length(),
                concatString(
                    getDigitArray(s, CARD_NUMBER_TOTAL_DIGITS),
                    CARD_NUMBER_DIVIDER_POSITION,
                    divider)
            );
          }
          break;
        //CC DATE
        case 1:
          if (!isInputCorrect(s,
              CARD_DATE_TOTAL_SYMBOLS,
              CARD_DATE_DIVIDER_MODULO,
              divider)) {
            s.replace(0, s.length(),
                concatString(
                    getDigitArray(s, CARD_DATE_TOTAL_DIGITS),
                    CARD_DATE_DIVIDER_POSITION,
                    divider));
          }
          break;
        //CC CVC
        case 2:
          if (s.length() > CARD_CVC_TOTAL_SYMBOLS) {
            s.delete(CARD_CVC_TOTAL_SYMBOLS, s.length());
          }
          break;
      }
    }

    private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
      boolean isCorrect = s.length() <= totalSymbols;
      for (int i = 0; i < s.length(); i++) {
        if (i > 0 && (i + 1) % dividerModulo == 0) {
          isCorrect &= divider == s.charAt(i);
        } else {
          isCorrect &= Character.isDigit(s.charAt(i));
        }
      }
      return isCorrect;
    }

    private String concatString(char[] digits, int dividerPosition, char divider) {
      final StringBuilder formatted = new StringBuilder();

      for (int i = 0; i < digits.length; i++) {
        if (digits[i] != 0) {
          formatted.append(digits[i]);
          if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
            formatted.append(divider);
          }
        }
      }

      return formatted.toString();
    }

    private char[] getDigitArray(final Editable s, final int size) {
      char[] digits = new char[size];
      int index = 0;
      for (int i = 0; i < s.length() && index < size; i++) {
        char current = s.charAt(i);
        if (Character.isDigit(current)) {
          digits[index] = current;
          index++;
        }
      }
      return digits;
    }

    public char getDivider() {
      return divider;
    }

    public void setDivider(char divider) {
      this.divider = divider;
    }

    public int getCcType() {
      return ccType;
    }

    public void setCcType(int ccType) {
      this.ccType = ccType;
    }
  }

}