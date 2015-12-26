package com.android.mms.view;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;

class AttachmentGroupView$a
  extends BaseAdapter
{
  public AttachmentGroupView$a(AttachmentGroupView paramAttachmentGroupView)
  {
    AttachmentItem.a();
  }
  
  public int getCount()
  {
    return AttachmentItem.getItemCount();
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {}
    for (paramView = (AttachmentItem)LayoutInflater.from(a.a).inflate(2130968604, null);; paramView = (AttachmentItem)paramView)
    {
      paramView.a(paramInt);
      paramView.setLayoutParams(new AbsListView.LayoutParams(-1, a.getResources().getDimensionPixelSize(2131558629)));
      return paramView;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.AttachmentGroupView.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */