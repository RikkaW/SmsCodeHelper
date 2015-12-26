package com.meizu.common.widget;

import android.view.View;
import android.view.ViewGroup;

public abstract interface PinnedHeaderListView$PinnedHeaderAdapter
{
  public abstract void configurePinnedHeaders(PinnedHeaderListView paramPinnedHeaderListView);
  
  public abstract int getPinnedHeaderCount();
  
  public abstract View getPinnedHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup);
  
  public abstract int getScrollPositionForHeader(int paramInt);
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PinnedHeaderListView.PinnedHeaderAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */