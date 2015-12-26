package com.android.mms.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

public class CmaContentFrm
  extends FrameLayout
{
  private int a = -1;
  private int b = 0;
  private AttachmentGroupView c;
  
  public CmaContentFrm(Context paramContext)
  {
    super(paramContext);
  }
  
  public CmaContentFrm(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CmaContentFrm(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void a(int paramInt)
  {
    paramInt = View.MeasureSpec.getSize(paramInt);
    if (paramInt < 0) {}
    int i;
    do
    {
      return;
      if (a < 0)
      {
        a = paramInt;
        return;
      }
      i = a - paramInt;
      a = paramInt;
    } while ((i == 0) || (c == null));
    if (i > 0)
    {
      c.setIsHide(true);
      return;
    }
    c.setIsShow(true);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((paramInt4 >= b) && (b != 0)) {
      if (c != null) {
        c.setIsKeybordShowing(false);
      }
    }
    for (;;)
    {
      if (b < paramInt4) {
        b = paramInt4;
      }
      return;
      if ((b != 0) && (c != null)) {
        c.setIsKeybordShowing(true);
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    a(paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setAttachmentGroupView(AttachmentGroupView paramAttachmentGroupView)
  {
    c = paramAttachmentGroupView;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.CmaContentFrm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */