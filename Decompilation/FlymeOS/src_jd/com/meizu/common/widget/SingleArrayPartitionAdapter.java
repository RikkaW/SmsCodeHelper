package com.meizu.common.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.List;

public abstract class SingleArrayPartitionAdapter<T>
  extends BasePartitionAdapter
{
  protected List<T> mObjects;
  
  public SingleArrayPartitionAdapter(Context paramContext)
  {
    super(paramContext);
  }
  
  public SingleArrayPartitionAdapter(Context paramContext, List<T> paramList, int... paramVarArgs) {}
  
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
  
  public int addPartition(BasePartitionAdapter.Partition paramPartition)
  {
    return super.addPartition(paramPartition);
  }
  
  public int addPartition(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    return addPartition(new BasePartitionAdapter.Partition(paramBoolean1, paramBoolean2, paramInt));
  }
  
  protected abstract void bindView(View paramView, Context paramContext, int paramInt1, int paramInt2, T paramT, int paramInt3, int paramInt4);
  
  public void changePartitions(List<T> paramList, int... paramVarArgs)
  {
    mObjects = paramList;
    setNotificationsEnabled(false);
    int i;
    if (paramVarArgs == null)
    {
      i = 0;
      if (i <= mPartitionCount) {
        break label69;
      }
    }
    int k;
    label69:
    for (int j = mPartitionCount;; j = i)
    {
      k = 0;
      while (k < j)
      {
        mPartitions[k].mItemCount = paramVarArgs[k];
        k += 1;
      }
      i = paramVarArgs.length;
      break;
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
      return;
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
  
  public void changePartitions(List<T> paramList, BasePartitionAdapter.Partition... paramVarArgs)
  {
    int i = 0;
    mObjects = paramList;
    setNotificationsEnabled(false);
    clearPartitions();
    if ((paramVarArgs != null) && (paramVarArgs.length > 0)) {
      while (i < paramVarArgs.length)
      {
        addPartition(paramVarArgs[i]);
        i += 1;
      }
    }
    setNotificationsEnabled(true);
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
  
  protected T getItem(int paramInt1, int paramInt2)
  {
    if (mObjects == null) {
      return null;
    }
    paramInt1 = getDataPosition(paramInt1, paramInt2);
    return (T)mObjects.get(paramInt1);
  }
  
  protected long getItemId(int paramInt1, int paramInt2)
  {
    if (mObjects == null) {
      return 0L;
    }
    return getDataPosition(paramInt1, paramInt2);
  }
  
  public List<T> getPartitionData()
  {
    return mObjects;
  }
  
  protected View getView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, View paramView, ViewGroup paramViewGroup)
  {
    if (mObjects == null) {
      throw new IllegalStateException("the list is null");
    }
    int i = getDataPosition(paramInt2, paramInt3);
    Object localObject = mObjects.get(i);
    if (paramView == null) {
      paramView = newView(mContext, paramInt1, paramInt2, localObject, paramInt3, paramInt4, paramViewGroup);
    }
    for (;;)
    {
      bindView(paramView, mContext, paramInt1, paramInt2, localObject, paramInt3, paramInt4);
      return paramView;
    }
  }
  
  protected abstract View newView(Context paramContext, int paramInt1, int paramInt2, T paramT, int paramInt3, int paramInt4, ViewGroup paramViewGroup);
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SingleArrayPartitionAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */