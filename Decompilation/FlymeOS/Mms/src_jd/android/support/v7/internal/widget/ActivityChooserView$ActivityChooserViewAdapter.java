package android.support.v7.internal.widget;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.string;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ActivityChooserView$ActivityChooserViewAdapter
  extends BaseAdapter
{
  private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
  private static final int ITEM_VIEW_TYPE_COUNT = 3;
  private static final int ITEM_VIEW_TYPE_FOOTER = 1;
  public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
  public static final int MAX_ACTIVITY_COUNT_UNLIMITED = Integer.MAX_VALUE;
  private ActivityChooserModel mDataModel;
  private boolean mHighlightDefaultActivity;
  private int mMaxActivityCount = 4;
  private boolean mShowDefaultActivity;
  private boolean mShowFooterView;
  
  private ActivityChooserView$ActivityChooserViewAdapter(ActivityChooserView paramActivityChooserView) {}
  
  public int getActivityCount()
  {
    return mDataModel.getActivityCount();
  }
  
  public int getCount()
  {
    int j = mDataModel.getActivityCount();
    int i = j;
    if (!mShowDefaultActivity)
    {
      i = j;
      if (mDataModel.getDefaultActivity() != null) {
        i = j - 1;
      }
    }
    j = Math.min(i, mMaxActivityCount);
    i = j;
    if (mShowFooterView) {
      i = j + 1;
    }
    return i;
  }
  
  public ActivityChooserModel getDataModel()
  {
    return mDataModel;
  }
  
  public ResolveInfo getDefaultActivity()
  {
    return mDataModel.getDefaultActivity();
  }
  
  public int getHistorySize()
  {
    return mDataModel.getHistorySize();
  }
  
  public Object getItem(int paramInt)
  {
    switch (getItemViewType(paramInt))
    {
    default: 
      throw new IllegalArgumentException();
    case 1: 
      return null;
    }
    int i = paramInt;
    if (!mShowDefaultActivity)
    {
      i = paramInt;
      if (mDataModel.getDefaultActivity() != null) {
        i = paramInt + 1;
      }
    }
    return mDataModel.getActivity(i);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public int getItemViewType(int paramInt)
  {
    if ((mShowFooterView) && (paramInt == getCount() - 1)) {
      return 1;
    }
    return 0;
  }
  
  public boolean getShowDefaultActivity()
  {
    return mShowDefaultActivity;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    switch (getItemViewType(paramInt))
    {
    default: 
      throw new IllegalArgumentException();
    case 1: 
      if (paramView != null)
      {
        localView = paramView;
        if (paramView.getId() == 1) {}
      }
      else
      {
        localView = LayoutInflater.from(this$0.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, paramViewGroup, false);
        localView.setId(1);
        ((TextView)localView.findViewById(R.id.title)).setText(this$0.getContext().getString(R.string.abc_activity_chooser_view_see_all));
      }
      return localView;
    }
    if (paramView != null)
    {
      localView = paramView;
      if (paramView.getId() == R.id.list_item) {}
    }
    else
    {
      localView = LayoutInflater.from(this$0.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, paramViewGroup, false);
    }
    paramView = this$0.getContext().getPackageManager();
    paramViewGroup = (ImageView)localView.findViewById(R.id.icon);
    ResolveInfo localResolveInfo = (ResolveInfo)getItem(paramInt);
    paramViewGroup.setImageDrawable(localResolveInfo.loadIcon(paramView));
    ((TextView)localView.findViewById(R.id.title)).setText(localResolveInfo.loadLabel(paramView));
    if ((mShowDefaultActivity) && (paramInt == 0) && (mHighlightDefaultActivity))
    {
      ViewCompat.setActivated(localView, true);
      return localView;
    }
    ViewCompat.setActivated(localView, false);
    return localView;
  }
  
  public int getViewTypeCount()
  {
    return 3;
  }
  
  public int measureContentWidth()
  {
    int i = 0;
    int k = mMaxActivityCount;
    mMaxActivityCount = Integer.MAX_VALUE;
    int m = View.MeasureSpec.makeMeasureSpec(0, 0);
    int n = View.MeasureSpec.makeMeasureSpec(0, 0);
    int i1 = getCount();
    View localView = null;
    int j = 0;
    while (i < i1)
    {
      localView = getView(i, localView, null);
      localView.measure(m, n);
      j = Math.max(j, localView.getMeasuredWidth());
      i += 1;
    }
    mMaxActivityCount = k;
    return j;
  }
  
  public void setDataModel(ActivityChooserModel paramActivityChooserModel)
  {
    ActivityChooserModel localActivityChooserModel = ActivityChooserView.access$000(this$0).getDataModel();
    if ((localActivityChooserModel != null) && (this$0.isShown())) {
      localActivityChooserModel.unregisterObserver(ActivityChooserView.access$1100(this$0));
    }
    mDataModel = paramActivityChooserModel;
    if ((paramActivityChooserModel != null) && (this$0.isShown())) {
      paramActivityChooserModel.registerObserver(ActivityChooserView.access$1100(this$0));
    }
    notifyDataSetChanged();
  }
  
  public void setMaxActivityCount(int paramInt)
  {
    if (mMaxActivityCount != paramInt)
    {
      mMaxActivityCount = paramInt;
      notifyDataSetChanged();
    }
  }
  
  public void setShowDefaultActivity(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((mShowDefaultActivity != paramBoolean1) || (mHighlightDefaultActivity != paramBoolean2))
    {
      mShowDefaultActivity = paramBoolean1;
      mHighlightDefaultActivity = paramBoolean2;
      notifyDataSetChanged();
    }
  }
  
  public void setShowFooterView(boolean paramBoolean)
  {
    if (mShowFooterView != paramBoolean)
    {
      mShowFooterView = paramBoolean;
      notifyDataSetChanged();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActivityChooserView.ActivityChooserViewAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */