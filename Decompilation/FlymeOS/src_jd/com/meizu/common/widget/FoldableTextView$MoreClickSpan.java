package com.meizu.common.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView.BufferType;

class FoldableTextView$MoreClickSpan
  extends ClickableSpan
{
  private final CharSequence mText;
  
  public FoldableTextView$MoreClickSpan(FoldableTextView paramFoldableTextView, CharSequence paramCharSequence)
  {
    mText = paramCharSequence;
  }
  
  public void onClick(View paramView)
  {
    if (FoldableTextView.access$100(this$0)) {}
    while ((FoldableTextView.access$200(this$0) != null) && (!FoldableTextView.access$200(this$0).onFolding(this$0, false))) {
      return;
    }
    FoldableTextView.access$302(this$0, false);
    this$0.setText(FoldableTextView.access$400(this$0), TextView.BufferType.NORMAL);
    FoldableTextView.access$500(this$0);
  }
  
  public void updateDrawState(TextPaint paramTextPaint)
  {
    if (FoldableTextView.access$000(this$0) == 0)
    {
      paramTextPaint.setColor(linkColor);
      return;
    }
    paramTextPaint.setColor(FoldableTextView.access$000(this$0));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.FoldableTextView.MoreClickSpan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */