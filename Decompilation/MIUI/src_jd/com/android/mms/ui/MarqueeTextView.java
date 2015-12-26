package com.android.mms.ui;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeTextView
  extends TextView
{
  public MarqueeTextView(Context paramContext)
  {
    super(paramContext);
  }
  
  public MarqueeTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public MarqueeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean isFocused()
  {
    return true;
  }
  
  public void setEllipsize(TextUtils.TruncateAt paramTruncateAt)
  {
    super.setEllipsize(TextUtils.TruncateAt.MARQUEE);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MarqueeTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */