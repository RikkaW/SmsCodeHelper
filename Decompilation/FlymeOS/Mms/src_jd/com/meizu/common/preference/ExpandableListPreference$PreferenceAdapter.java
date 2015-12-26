package com.meizu.common.preference;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import com.meizu.common.R.dimen;
import com.meizu.common.R.layout;

class ExpandableListPreference$PreferenceAdapter
  extends BaseAdapter
{
  private Context mContext;
  private CharSequence[] mData;
  private ListView mList;
  private int mSelectedPosition = -1;
  
  public ExpandableListPreference$PreferenceAdapter(ExpandableListPreference paramExpandableListPreference, Context paramContext, CharSequence[] paramArrayOfCharSequence)
  {
    mContext = paramContext;
    mData = paramArrayOfCharSequence;
  }
  
  public int getCount()
  {
    return mData.length;
  }
  
  public Object getItem(int paramInt)
  {
    return mData[paramInt];
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = new Holder(null);
      paramViewGroup = ((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(R.layout.mc_expandable_preference_list_item, null);
      checkedTextView = ((CheckedTextView)paramViewGroup);
      paramViewGroup.setLayoutParams(new AbsListView.LayoutParams(-1, mContext.getResources().getDimensionPixelOffset(R.dimen.mc_expandable_preference_list_item_height)));
      paramViewGroup.setTag(paramView);
    }
    for (;;)
    {
      checkedTextView.setText(mData[paramInt]);
      if (paramInt == mSelectedPosition) {
        mList.setItemChecked(paramInt, true);
      }
      return paramViewGroup;
      Holder localHolder = (Holder)paramView.getTag();
      paramViewGroup = paramView;
      paramView = localHolder;
    }
  }
  
  public void setSelectedPosition(int paramInt)
  {
    mSelectedPosition = paramInt;
  }
  
  public void setTargetList(ListView paramListView)
  {
    mList = paramListView;
  }
  
  class Holder
  {
    public CheckedTextView checkedTextView;
    
    private Holder() {}
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference.PreferenceAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */