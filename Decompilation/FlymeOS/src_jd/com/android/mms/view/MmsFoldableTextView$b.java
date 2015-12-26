package com.android.mms.view;

import android.text.Selection;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

class MmsFoldableTextView$b
  extends ClickableSpan
{
  private MmsFoldableTextView$b(MmsFoldableTextView paramMmsFoldableTextView) {}
  
  public void onClick(View paramView)
  {
    if (MmsFoldableTextView.a(a) == null) {}
    do
    {
      return;
      MmsFoldableTextView.a(a).a(a);
    } while (!(a.getText() instanceof Spannable));
    Selection.setSelection((Spannable)a.getText(), a.getText().length());
  }
  
  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.setColor(linkColor);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsFoldableTextView.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */