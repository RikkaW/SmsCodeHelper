package com.meizu.common.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

public abstract class MultiCursorPartitionAdapter
  extends BasePartitionAdapter
{
  public MultiCursorPartitionAdapter(Context paramContext)
  {
    super(paramContext);
  }
  
  public MultiCursorPartitionAdapter(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  public int addPartition(CursorPartition paramCursorPartition)
  {
    return super.addPartition(paramCursorPartition);
  }
  
  public int addPartition(boolean paramBoolean1, boolean paramBoolean2, Cursor paramCursor)
  {
    return addPartition(new CursorPartition(paramBoolean1, paramBoolean2, paramCursor));
  }
  
  protected abstract void bindView(View paramView, Context paramContext, int paramInt1, int paramInt2, Cursor paramCursor, int paramInt3, int paramInt4);
  
  public void changeCursor(int paramInt, Cursor paramCursor)
  {
    paramCursor = swapCursor(paramInt, paramCursor);
    if ((paramCursor != null) && (!paramCursor.isClosed())) {
      paramCursor.close();
    }
  }
  
  public void clearCursors()
  {
    int i = 0;
    while (i < mPartitionCount)
    {
      CursorPartition localCursorPartition = getPartition(i);
      mCursor = null;
      mItemCount = 0;
      i += 1;
    }
    invalidate();
    notifyDataSetChanged();
  }
  
  public void clearPartitions()
  {
    int i = 0;
    while (i < mPartitionCount)
    {
      CursorPartition localCursorPartition = getPartition(i);
      Cursor localCursor = mCursor;
      if ((localCursor != null) && (!localCursor.isClosed()))
      {
        localCursor.close();
        mCursor = null;
      }
      i += 1;
    }
    super.clearPartitions();
  }
  
  public Cursor getCursor(int paramInt)
  {
    return getPartitionmCursor;
  }
  
  public int getDataPosition(int paramInt)
  {
    int i = 0;
    ensureCacheValid();
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        j = paramInt - j;
        paramInt = j;
        if (mPartitions[i].mHasHeader) {
          paramInt = j - 1;
        }
        j = mPartitions[i].mCount;
        k = mPartitions[i].mFooterViewsCount;
        if ((paramInt >= mPartitions[i].mHeaderViewsCount) && (paramInt < j - k)) {
          return getDataPosition(i, paramInt);
        }
        return -1;
      }
      i += 1;
    }
    return -1;
  }
  
  protected int getDataPosition(int paramInt1, int paramInt2)
  {
    return paramInt2 - mPartitions[paramInt1].mHeaderViewsCount;
  }
  
  protected Object getItem(int paramInt1, int paramInt2)
  {
    Cursor localCursor = getPartitionmCursor;
    Object localObject;
    if ((localCursor == null) || (localCursor.isClosed())) {
      localObject = null;
    }
    do
    {
      return localObject;
      paramInt1 = getDataPosition(paramInt1, paramInt2);
      if (paramInt1 < 0) {
        break;
      }
      localObject = localCursor;
    } while (localCursor.moveToPosition(paramInt1));
    return null;
  }
  
  protected long getItemId(int paramInt1, int paramInt2)
  {
    CursorPartition localCursorPartition = getPartition(paramInt1);
    if (mRowIDColumnIndex == -1) {}
    Cursor localCursor;
    do
    {
      do
      {
        return 0L;
        localCursor = mCursor;
      } while ((localCursor == null) || (localCursor.isClosed()));
      paramInt1 = getDataPosition(paramInt1, paramInt2);
    } while ((paramInt1 < 0) || (!localCursor.moveToPosition(paramInt1)));
    return localCursor.getLong(mRowIDColumnIndex);
  }
  
  public CursorPartition getPartition(int paramInt)
  {
    return (CursorPartition)super.getPartition(paramInt);
  }
  
  protected View getView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, View paramView, ViewGroup paramViewGroup)
  {
    Cursor localCursor = getPartitionmCursor;
    if (localCursor == null) {
      throw new IllegalStateException("the partition " + paramInt2 + " cursor is null");
    }
    int i = getDataPosition(paramInt2, paramInt3);
    if (!localCursor.moveToPosition(i)) {
      throw new IllegalStateException("Couldn't move cursor to position " + i);
    }
    if (paramView == null) {
      paramView = newView(mContext, paramInt1, paramInt2, localCursor, paramInt3, paramInt4, paramViewGroup);
    }
    for (;;)
    {
      bindView(paramView, mContext, paramInt1, paramInt2, localCursor, paramInt3, paramInt4);
      return paramView;
    }
  }
  
  protected abstract View newView(Context paramContext, int paramInt1, int paramInt2, Cursor paramCursor, int paramInt3, int paramInt4, ViewGroup paramViewGroup);
  
  public void removePartition(int paramInt)
  {
    CursorPartition localCursorPartition = getPartition(paramInt);
    Cursor localCursor = mCursor;
    if ((localCursor != null) && (!localCursor.isClosed()))
    {
      localCursor.close();
      mCursor = null;
    }
    super.removePartition(paramInt);
  }
  
  public Cursor swapCursor(int paramInt, Cursor paramCursor)
  {
    int i = 0;
    CursorPartition localCursorPartition = getPartition(paramInt);
    Cursor localCursor = mCursor;
    if (localCursor == paramCursor) {
      return null;
    }
    mCursor = paramCursor;
    if (paramCursor != null)
    {
      mRowIDColumnIndex = paramCursor.getColumnIndex("_id");
      paramInt = i;
      if (!paramCursor.isClosed()) {
        paramInt = paramCursor.getCount();
      }
    }
    for (mItemCount = paramInt;; mItemCount = 0)
    {
      invalidate();
      notifyDataSetChanged();
      return localCursor;
    }
  }
  
  public static class CursorPartition
    extends BasePartitionAdapter.Partition
  {
    Cursor mCursor;
    int mRowIDColumnIndex;
    
    public CursorPartition(boolean paramBoolean1, boolean paramBoolean2, Cursor paramCursor) {}
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiCursorPartitionAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */