package com.android.mms.view;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import arj;

class MmsSlidePartitionItemLayout$b
  implements ViewTreeObserver.OnPreDrawListener
{
  private MmsSlidePartitionItemLayout$b(MmsSlidePartitionItemLayout paramMmsSlidePartitionItemLayout) {}
  
  public boolean onPreDraw()
  {
    MmsSlidePartitionItemLayout.a(a).getViewTreeObserver().removeOnPreDrawListener(this);
    if ((MmsSlidePartitionItemLayout.a(a) != null) && (MmsSlidePartitionItemLayout.b(a).isShowing())) {
      MmsSlidePartitionItemLayout.b(a).a(MmsSlidePartitionItemLayout.a(a), new RectF(0.0F, 0.0F, MmsSlidePartitionItemLayout.a(a).getWidth(), MmsSlidePartitionItemLayout.a(a).getHeight()));
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsSlidePartitionItemLayout.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */