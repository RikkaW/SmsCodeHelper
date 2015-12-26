package com.meizu.common.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class PinnedHeaderListAdapter
  extends MultiCursorPartitionAdapter
  implements PinnedHeaderListView.PinnedHeaderAdapter
{
  public static final int PARTITION_HEADER_TYPE = 0;
  private boolean[] mHeaderVisibility;
  private boolean mPinnedPartitionHeadersEnabled;
  
  public PinnedHeaderListAdapter(Context paramContext)
  {
    super(paramContext);
  }
  
  public PinnedHeaderListAdapter(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  public void configurePinnedHeaders(PinnedHeaderListView paramPinnedHeaderListView)
  {
    if (!mPinnedPartitionHeadersEnabled) {
      return;
    }
    int k = getPartitionCount();
    if ((mHeaderVisibility == null) || (mHeaderVisibility.length != k)) {
      mHeaderVisibility = new boolean[k];
    }
    int i = 0;
    boolean bool;
    while (i < k)
    {
      bool = isPinnedPartitionHeaderVisible(i);
      mHeaderVisibility[i] = bool;
      if (!bool) {
        paramPinnedHeaderListView.setHeaderInvisible(i, true);
      }
      i += 1;
    }
    int i2 = paramPinnedHeaderListView.getHeaderViewsCount();
    i = 0;
    int n = 0;
    int j = -1;
    label93:
    int i1;
    int m;
    if (i < k)
    {
      i1 = n;
      m = j;
      if (mHeaderVisibility[i] == 0) {
        break label238;
      }
      if (i <= getPartitionForPosition(paramPinnedHeaderListView.getPositionAt(n) - i2)) {}
    }
    else
    {
      i1 = paramPinnedHeaderListView.getHeight();
      i = k;
      m = 0;
    }
    label238:
    label252:
    label331:
    for (;;)
    {
      i -= 1;
      int i3;
      if (i > j)
      {
        if (mHeaderVisibility[i] == 0) {
          break label331;
        }
        i3 = paramPinnedHeaderListView.getPositionAt(i1 - m) - i2;
        if (i3 >= 0) {
          break label252;
        }
      }
      do
      {
        i = j + 1;
        while (i < k)
        {
          if (mHeaderVisibility[i] != 0) {
            paramPinnedHeaderListView.setHeaderInvisible(i, isPartitionEmpty(i));
          }
          i += 1;
        }
        break;
        paramPinnedHeaderListView.setHeaderPinnedAtTop(i, n, false);
        i1 = n + paramPinnedHeaderListView.getPinnedHeaderHeight(i);
        m = i;
        i += 1;
        n = i1;
        j = m;
        break label93;
        n = getPartitionForPosition(i3 - 1);
      } while ((n == -1) || (i <= n));
      n = m + paramPinnedHeaderListView.getPinnedHeaderHeight(i);
      if (i3 < getPositionForPartition(i)) {}
      for (bool = true;; bool = false)
      {
        paramPinnedHeaderListView.setHeaderPinnedAtBottom(i, i1 - n, bool);
        m = i;
        k = i;
        i = m;
        m = n;
        break;
      }
    }
  }
  
  public int getPinnedHeaderCount()
  {
    if (mPinnedPartitionHeadersEnabled) {
      return getPartitionCount();
    }
    return 0;
  }
  
  public View getPinnedHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    if (hasHeader(paramInt))
    {
      if (paramView == null) {
        break label101;
      }
      localObject = (Integer)paramView.getTag();
      if ((localObject == null) || (((Integer)localObject).intValue() != 0)) {
        break label101;
      }
    }
    for (;;)
    {
      int i = getPositionForPartition(paramInt);
      localObject = paramView;
      if (paramView == null)
      {
        localObject = newHeaderView(mContext, i, paramInt, paramViewGroup);
        ((View)localObject).setTag(Integer.valueOf(0));
        ((View)localObject).setFocusable(false);
        ((View)localObject).setEnabled(false);
      }
      bindHeaderView((View)localObject, mContext, i, paramInt);
      return (View)localObject;
      return null;
      label101:
      paramView = null;
    }
  }
  
  public boolean getPinnedPartitionHeadersEnabled()
  {
    return mPinnedPartitionHeadersEnabled;
  }
  
  public int getScrollPositionForHeader(int paramInt)
  {
    return getPositionForPartition(paramInt);
  }
  
  protected boolean isPinnedPartitionHeaderVisible(int paramInt)
  {
    return (mPinnedPartitionHeadersEnabled) && (hasHeader(paramInt)) && (!isPartitionEmpty(paramInt));
  }
  
  public void setPinnedPartitionHeadersEnabled(boolean paramBoolean)
  {
    mPinnedPartitionHeadersEnabled = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PinnedHeaderListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */