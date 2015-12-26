package com.android.mms.ui;

import android.content.Context;
import android.view.View;
import android.widget.MediaController;
import android.widget.ScrollView;

class SlideView$2
  extends ScrollView
{
  private int mBottomY;
  
  SlideView$2(SlideView paramSlideView, Context paramContext)
  {
    super(paramContext);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (getChildCount() > 0)
    {
      paramInt2 = getChildAt(0).getHeight();
      paramInt3 = getHeight();
      paramInt1 = i;
      if (paramInt3 < paramInt2) {
        paramInt1 = paramInt2 - paramInt3;
      }
      mBottomY = paramInt1;
    }
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (((paramInt2 == 0) || (paramInt2 >= mBottomY)) && (SlideView.access$700(this$0) != null)) {
      SlideView.access$700(this$0).show();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideView.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */