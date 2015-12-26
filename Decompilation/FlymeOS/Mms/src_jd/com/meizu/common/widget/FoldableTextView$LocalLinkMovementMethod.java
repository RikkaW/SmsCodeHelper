package com.meizu.common.widget;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

public class FoldableTextView$LocalLinkMovementMethod
  extends LinkMovementMethod
{
  static LocalLinkMovementMethod sInstance;
  
  public static LocalLinkMovementMethod getInstance()
  {
    if (sInstance == null) {
      sInstance = new LocalLinkMovementMethod();
    }
    return sInstance;
  }
  
  public boolean onTouchEvent(TextView paramTextView, Spannable paramSpannable, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i == 1) || (i == 0))
    {
      int j = (int)paramMotionEvent.getX();
      int k = (int)paramMotionEvent.getY();
      int m = paramTextView.getTotalPaddingLeft();
      int n = paramTextView.getTotalPaddingTop();
      int i1 = paramTextView.getScrollX();
      int i2 = paramTextView.getScrollY();
      Object localObject = paramTextView.getLayout();
      j = ((Layout)localObject).getOffsetForHorizontal(((Layout)localObject).getLineForVertical(k - n + i2), j - m + i1);
      localObject = (ClickableSpan[])paramSpannable.getSpans(j, j, ClickableSpan.class);
      if (localObject.length != 0)
      {
        if (i == 1) {
          localObject[0].onClick(paramTextView);
        }
        for (;;)
        {
          if ((paramTextView instanceof FoldableTextView)) {
            FoldableTextView.access$1002((FoldableTextView)paramTextView, true);
          }
          return true;
          if (i == 0) {
            Selection.setSelection(paramSpannable, paramSpannable.getSpanStart(localObject[0]), paramSpannable.getSpanEnd(localObject[0]));
          }
        }
      }
      Selection.removeSelection(paramSpannable);
      Touch.onTouchEvent(paramTextView, paramSpannable, paramMotionEvent);
      return false;
    }
    return Touch.onTouchEvent(paramTextView, paramSpannable, paramMotionEvent);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.FoldableTextView.LocalLinkMovementMethod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */