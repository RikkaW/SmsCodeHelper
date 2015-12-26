package com.meizu.common.widget;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public abstract class MultiArrayPartitionAdapter<T>
  extends BasePartitionAdapter
{
  public MultiArrayPartitionAdapter(Context paramContext)
  {
    super(paramContext);
  }
  
  public MultiArrayPartitionAdapter(Context paramContext, List<T>... paramVarArgs) {}
  
  public int addPartition(ArrayPartition paramArrayPartition)
  {
    return super.addPartition(paramArrayPartition);
  }
  
  public int addPartition(boolean paramBoolean1, boolean paramBoolean2, List<T> paramList)
  {
    return addPartition(new ArrayPartition(paramBoolean1, paramBoolean2, paramList));
  }
  
  public void addPartitions(List<T>... paramVarArgs)
  {
    if ((paramVarArgs != null) && (paramVarArgs.length > 0))
    {
      setNotificationsEnabled(false);
      int i = 0;
      while (i < paramVarArgs.length)
      {
        addPartition(false, true, paramVarArgs[i]);
        i += 1;
      }
      setNotificationsEnabled(true);
    }
  }
  
  protected abstract void bindView(View paramView, Context paramContext, int paramInt1, int paramInt2, T paramT, int paramInt3, int paramInt4);
  
  public void changePartition(int paramInt, List<T> paramList)
  {
    ArrayPartition localArrayPartition = getPartition(paramInt);
    mObjects = paramList;
    if (paramList == null) {}
    for (paramInt = 0;; paramInt = paramList.size())
    {
      mItemCount = paramInt;
      invalidate();
      notifyDataSetChanged();
      return;
    }
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
  
  protected T getItem(int paramInt1, int paramInt2)
  {
    List localList = getPartitionmObjects;
    if (localList == null) {}
    do
    {
      return null;
      paramInt1 = getDataPosition(paramInt1, paramInt2);
    } while (paramInt1 < 0);
    return (T)localList.get(paramInt1);
  }
  
  protected long getItemId(int paramInt1, int paramInt2)
  {
    if (getPartitionmObjects == null) {}
    do
    {
      return 0L;
      paramInt1 = getDataPosition(paramInt1, paramInt2);
    } while (paramInt1 < 0);
    return paramInt1;
  }
  
  public ArrayPartition getPartition(int paramInt)
  {
    return (ArrayPartition)super.getPartition(paramInt);
  }
  
  public List<T> getPartitionData(int paramInt)
  {
    return getPartitionmObjects;
  }
  
  protected View getView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject = getPartitionmObjects;
    if (localObject == null) {
      throw new IllegalStateException("the partition " + paramInt2 + " list is null");
    }
    if (((List)localObject).size() <= 0)
    {
      Log.w("IndexOutOfBounds", "MultiArrayPartitionAdapter getView exception, List partition item size :" + ((List)localObject).size());
      throw new IndexOutOfBoundsException("APP数据集为空:请先检查数据集中书否有数据,然后再访问!");
    }
    int i = getDataPosition(paramInt2, paramInt3);
    if (i >= ((List)localObject).size())
    {
      Log.w("IndexOutOfBounds", "MultiArrayPartitionAdapter getView exception, List partition item size :" + ((List)localObject).size() + ", listIndex :" + i);
      throw new IndexOutOfBoundsException("APP越界操作:当前数据集大小为:" + ((List)localObject).size() + ",有效访问范围为:0~" + (((List)localObject).size() - 1) + ",当前访问序号为:" + i + ",非法,请处理！");
    }
    localObject = ((List)localObject).get(i);
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
  
  public static class ArrayPartition
    extends BasePartitionAdapter.Partition
  {
    List mObjects;
    
    public ArrayPartition(boolean paramBoolean1, boolean paramBoolean2, List paramList) {}
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiArrayPartitionAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */