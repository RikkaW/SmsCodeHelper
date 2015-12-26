package com.android.mms.ui;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.TextAppearanceSpan;

public class TextSizeAdjustSpan
  extends TextAppearanceSpan
{
  private boolean mNeedUnderline;
  
  public TextSizeAdjustSpan(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  public void needUnderline()
  {
    mNeedUnderline = true;
  }
  
  public void updateDrawState(TextPaint paramTextPaint)
  {
    super.updateDrawState(paramTextPaint);
    if (mNeedUnderline) {
      paramTextPaint.setUnderlineText(true);
    }
  }
  
  public void updateMeasureState(TextPaint paramTextPaint)
  {
    float f = paramTextPaint.getTextSize();
    super.updateMeasureState(paramTextPaint);
    if ((f > 0.0F) && (getTextSize() > 0)) {
      paramTextPaint.setTextSize(f);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.TextSizeAdjustSpan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */