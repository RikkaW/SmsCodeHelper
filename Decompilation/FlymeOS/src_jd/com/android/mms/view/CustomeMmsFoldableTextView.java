package com.android.mms.view;

import ael;
import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomeMmsFoldableTextView
  extends MmsFoldableTextView
{
  ClickableSpan[] a;
  
  public CustomeMmsFoldableTextView(Context paramContext)
  {
    super(paramContext);
  }
  
  public CustomeMmsFoldableTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CustomeMmsFoldableTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    }
    for (;;)
    {
      return true;
      int i = getOffsetForPosition(paramMotionEvent.getX(), paramMotionEvent.getY());
      paramMotionEvent = getText();
      if ((paramMotionEvent == null) || (!(paramMotionEvent instanceof Spanned))) {
        return false;
      }
      a = ((ClickableSpan[])((Spanned)paramMotionEvent).getSpans(i, i, ClickableSpan.class));
      return a.length > 0;
      if (a == null)
      {
        i = getOffsetForPosition(paramMotionEvent.getX(), paramMotionEvent.getY());
        a = ((ClickableSpan[])((Spannable)getText()).getSpans(i, i, ClickableSpan.class));
      }
      if ((a != null) && (a.length > 0))
      {
        ael.a(a[0], this);
        a = null;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.CustomeMmsFoldableTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */