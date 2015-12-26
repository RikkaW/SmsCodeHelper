package com.android.mms.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

class ConversationList$b
  extends PagerAdapter
{
  private final FragmentManager b;
  private FragmentTransaction c = null;
  private Fragment d;
  
  public ConversationList$b(ConversationList paramConversationList)
  {
    b = paramConversationList.getFragmentManager();
  }
  
  private Fragment a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("position: " + paramInt);
    case 0: 
      return a.c;
    }
    return ConversationList.b(a);
  }
  
  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    if (c == null) {
      c = b.beginTransaction();
    }
    c.hide((Fragment)paramObject);
  }
  
  public void finishUpdate(ViewGroup paramViewGroup)
  {
    if (c != null)
    {
      c.commitAllowingStateLoss();
      c = null;
      b.executePendingTransactions();
    }
  }
  
  public int getCount()
  {
    return 2;
  }
  
  public int getItemPosition(Object paramObject)
  {
    if (paramObject == a.c) {
      return 0;
    }
    if (paramObject == ConversationList.b(a)) {
      return 1;
    }
    return -2;
  }
  
  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    if (c == null) {
      c = b.beginTransaction();
    }
    paramViewGroup = a(paramInt);
    c.show(paramViewGroup);
    if (paramViewGroup == d) {}
    for (boolean bool = true;; bool = false)
    {
      paramViewGroup.setUserVisibleHint(bool);
      return paramViewGroup;
    }
  }
  
  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    if (paramObject == null) {}
    while (((Fragment)paramObject).getView() != paramView) {
      return false;
    }
    return true;
  }
  
  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {}
  
  public Parcelable saveState()
  {
    return null;
  }
  
  public void setPrimaryItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramViewGroup = (Fragment)paramObject;
    if (d != paramViewGroup)
    {
      if (d != null) {
        d.setUserVisibleHint(false);
      }
      if (paramViewGroup != null) {
        paramViewGroup.setUserVisibleHint(true);
      }
      d = paramViewGroup;
    }
  }
  
  public void startUpdate(ViewGroup paramViewGroup) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationList.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */