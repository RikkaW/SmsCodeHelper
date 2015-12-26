package com.android.mms.quickreply;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class CustomeScrollView
  extends ScrollView
{
  private a a;
  
  public CustomeScrollView(Context paramContext)
  {
    super(paramContext);
  }
  
  public CustomeScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CustomeScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (((getContext() instanceof EditQuickReplyActivity)) && (a != null))
    {
      if (paramInt4 > paramInt2) {
        a.a(true);
      }
    }
    else {
      return;
    }
    a.a(false);
  }
  
  public void setOnSizeChangedCallback(a parama)
  {
    a = parama;
  }
  
  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.quickreply.CustomeScrollView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */