package com.meizu.common.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

public abstract class SingleCursorPartitionAdapter
  extends BasePartitionAdapter
{
  public static final int FLAG_REGISTER_CONTENT_OBSERVER = 1;
  protected ChangeObserver mChangeObserver;
  protected Cursor mCursor;
  protected DataSetObserver mDataSetObserver;
  protected int mRowIDColumnIndex;
  
  public SingleCursorPartitionAdapter(Context paramContext, int paramInt)
  {
    super(paramContext);
    init(null, paramInt);
  }
  
  public SingleCursorPartitionAdapter(Context paramContext, Cursor paramCursor, int[] paramArrayOfInt, int paramInt) {}
  
  private void addPartitions(int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt != null) && (paramArrayOfInt.length > 0))
    {
      setNotificationsEnabled(false);
      int i = 0;
      while (i < paramArrayOfInt.length)
      {
        addPartition(false, true, paramArrayOfInt[i]);
        i += 1;
      }
      setNotificationsEnabled(true);
    }
  }
  
  private void init(Cursor paramCursor, int paramInt)
  {
    int i;
    int j;
    if (paramCursor != null)
    {
      i = 1;
      mCursor = paramCursor;
      if (i == 0) {
        break label107;
      }
      j = paramCursor.getColumnIndexOrThrow("_id");
      label25:
      mRowIDColumnIndex = j;
      if ((paramInt & 0x1) != 1) {
        break label113;
      }
      mChangeObserver = new ChangeObserver();
    }
    for (mDataSetObserver = new MyDataSetObserver(null);; mDataSetObserver = null)
    {
      if (i != 0)
      {
        if (mChangeObserver != null) {
          paramCursor.registerContentObserver(mChangeObserver);
        }
        if (mDataSetObserver != null) {
          paramCursor.registerDataSetObserver(mDataSetObserver);
        }
      }
      return;
      i = 0;
      break;
      label107:
      j = -1;
      break label25;
      label113:
      mChangeObserver = null;
    }
  }
  
  public int addPartition(BasePartitionAdapter.Partition paramPartition)
  {
    return super.addPartition(paramPartition);
  }
  
  public int addPartition(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    return addPartition(new BasePartitionAdapter.Partition(paramBoolean1, paramBoolean2, paramInt));
  }
  
  protected abstract void bindView(View paramView, Context paramContext, int paramInt1, int paramInt2, Cursor paramCursor, int paramInt3, int paramInt4);
  
  public void changeCursor(Cursor paramCursor, int... paramVarArgs)
  {
    paramCursor = swapCursor(paramCursor, paramVarArgs);
    if (paramCursor != null) {
      paramCursor.close();
    }
  }
  
  public void changeCursor(Cursor paramCursor, BasePartitionAdapter.Partition... paramVarArgs)
  {
    paramCursor = swapCursor(paramCursor, paramVarArgs);
    if (paramCursor != null) {
      paramCursor.close();
    }
  }
  
  public Cursor getCursor()
  {
    return mCursor;
  }
  
  public int getDataPosition(int paramInt)
  {
    int j = 0;
    ensureCacheValid();
    int k = 0;
    int i = 0;
    while (j < mPartitionCount)
    {
      int m = mPartitions[j].mSize + k;
      if ((paramInt >= k) && (paramInt < m))
      {
        k = paramInt - k;
        paramInt = k;
        if (mPartitions[j].mHasHeader) {
          paramInt = k - 1;
        }
        k = mPartitions[j].mCount;
        m = mPartitions[j].mFooterViewsCount;
        if ((paramInt >= mPartitions[j].mHeaderViewsCount) && (paramInt < k - m)) {
          return paramInt - mPartitions[j].mHeaderViewsCount + i;
        }
        return -1;
      }
      k = mPartitions[j].mItemCount;
      j += 1;
      i = k + i;
      k = m;
    }
    throw new ArrayIndexOutOfBoundsException(paramInt);
  }
  
  protected int getDataPosition(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = 0;
    while (i < paramInt1)
    {
      j += mPartitions[i].mItemCount;
      i += 1;
    }
    return paramInt2 - mPartitions[paramInt1].mHeaderViewsCount + j;
  }
  
  protected Object getItem(int paramInt1, int paramInt2)
  {
    if ((mCursor == null) || (mCursor.isClosed()) || (mCursor.getCount() == 0)) {}
    do
    {
      return null;
      paramInt1 = getDataPosition(paramInt1, paramInt2);
    } while (!mCursor.moveToPosition(paramInt1));
    return mCursor;
  }
  
  protected long getItemId(int paramInt1, int paramInt2)
  {
    if (mRowIDColumnIndex == -1) {}
    do
    {
      do
      {
        return 0L;
      } while ((mCursor == null) || (mCursor.isClosed()) || (mCursor.getCount() == 0));
      paramInt1 = getDataPosition(paramInt1, paramInt2);
    } while (!mCursor.moveToPosition(paramInt1));
    return mCursor.getLong(mRowIDColumnIndex);
  }
  
  protected View getView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, View paramView, ViewGroup paramViewGroup)
  {
    if (mCursor == null) {
      throw new IllegalStateException("the cursor is null");
    }
    int i = getDataPosition(paramInt2, paramInt3);
    if (!mCursor.moveToPosition(i)) {
      throw new IllegalStateException("Couldn't move cursor to position " + i);
    }
    if (paramView == null) {
      paramView = newView(mContext, paramInt1, paramInt2, mCursor, paramInt3, paramInt4, paramViewGroup);
    }
    for (;;)
    {
      bindView(paramView, mContext, paramInt1, paramInt2, mCursor, paramInt3, paramInt4);
      return paramView;
    }
  }
  
  public boolean hasStableIds()
  {
    return true;
  }
  
  protected abstract View newView(Context paramContext, int paramInt1, int paramInt2, Cursor paramCursor, int paramInt3, int paramInt4, ViewGroup paramViewGroup);
  
  protected void onContentChanged() {}
  
  public Cursor swapCursor(Cursor paramCursor, int... paramVarArgs)
  {
    Cursor localCursor = mCursor;
    int i;
    if (paramCursor == mCursor)
    {
      paramCursor = null;
      setNotificationsEnabled(false);
      if (paramVarArgs != null) {
        break label187;
      }
      i = 0;
      label27:
      if (i <= mPartitionCount) {
        break label193;
      }
    }
    int k;
    label187:
    label193:
    for (int j = mPartitionCount;; j = i)
    {
      k = 0;
      while (k < j)
      {
        mPartitions[k].mItemCount = paramVarArgs[k];
        k += 1;
      }
      if (localCursor != null)
      {
        if (mChangeObserver != null) {
          localCursor.unregisterContentObserver(mChangeObserver);
        }
        if (mDataSetObserver != null) {
          localCursor.unregisterDataSetObserver(mDataSetObserver);
        }
      }
      mCursor = paramCursor;
      if (paramCursor != null)
      {
        if (mChangeObserver != null) {
          paramCursor.registerContentObserver(mChangeObserver);
        }
        if (mDataSetObserver != null) {
          paramCursor.registerDataSetObserver(mDataSetObserver);
        }
        mRowIDColumnIndex = paramCursor.getColumnIndexOrThrow("_id");
        paramCursor = localCursor;
        break;
      }
      mRowIDColumnIndex = -1;
      paramCursor = localCursor;
      break;
      i = paramVarArgs.length;
      break label27;
    }
    if (mPartitionCount > j)
    {
      Arrays.fill(mPartitions, j, mPartitionCount, null);
      mPartitionCount = j;
    }
    for (;;)
    {
      invalidate();
      notifyDataSetChanged();
      setNotificationsEnabled(true);
      return paramCursor;
      if (i > j)
      {
        k = 0;
        while (k < i - j)
        {
          addPartition(false, true, paramVarArgs[(j + k)]);
          k += 1;
        }
      }
    }
  }
  
  public Cursor swapCursor(Cursor paramCursor, BasePartitionAdapter.Partition... paramVarArgs)
  {
    int i = 0;
    Cursor localCursor = mCursor;
    if (paramCursor == mCursor) {
      paramCursor = null;
    }
    for (;;)
    {
      setNotificationsEnabled(false);
      clearPartitions();
      if ((paramVarArgs == null) || (paramVarArgs.length <= 0)) {
        break;
      }
      while (i < paramVarArgs.length)
      {
        addPartition(paramVarArgs[i]);
        i += 1;
      }
      if (localCursor != null)
      {
        if (mChangeObserver != null) {
          localCursor.unregisterContentObserver(mChangeObserver);
        }
        if (mDataSetObserver != null) {
          localCursor.unregisterDataSetObserver(mDataSetObserver);
        }
      }
      mCursor = paramCursor;
      if (paramCursor != null)
      {
        if (mChangeObserver != null) {
          paramCursor.registerContentObserver(mChangeObserver);
        }
        if (mDataSetObserver != null) {
          paramCursor.registerDataSetObserver(mDataSetObserver);
        }
        mRowIDColumnIndex = paramCursor.getColumnIndexOrThrow("_id");
        paramCursor = localCursor;
      }
      else
      {
        mRowIDColumnIndex = -1;
        paramCursor = localCursor;
      }
    }
    setNotificationsEnabled(true);
    return paramCursor;
  }
  
  class ChangeObserver
    extends ContentObserver
  {
    public ChangeObserver()
    {
      super();
    }
    
    public boolean deliverSelfNotifications()
    {
      return true;
    }
    
    public void onChange(boolean paramBoolean)
    {
      onContentChanged();
    }
  }
  
  class MyDataSetObserver
    extends DataSetObserver
  {
    private MyDataSetObserver() {}
    
    public void onChanged()
    {
      notifyDataSetChanged();
    }
    
    public void onInvalidated()
    {
      notifyDataSetInvalidated();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SingleCursorPartitionAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */