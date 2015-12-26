package com.android.mms.ui;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class AlertDialogHelper$UrlSpan
  extends ClickableSpan
{
  private UrlSpanOnClickListener mOnClickListener;
  
  public AlertDialogHelper$UrlSpan(UrlSpanOnClickListener paramUrlSpanOnClickListener)
  {
    mOnClickListener = paramUrlSpanOnClickListener;
  }
  
  public void onClick(View paramView)
  {
    if (mOnClickListener != null) {
      mOnClickListener.onClick();
    }
  }
  
  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.setUnderlineText(true);
    paramTextPaint.setColor(-16776961);
  }
  
  public static abstract interface UrlSpanOnClickListener
  {
    public abstract void onClick();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AlertDialogHelper.UrlSpan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */