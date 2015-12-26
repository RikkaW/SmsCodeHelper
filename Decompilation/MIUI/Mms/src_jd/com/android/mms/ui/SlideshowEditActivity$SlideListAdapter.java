package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;

class SlideshowEditActivity$SlideListAdapter
  extends ArrayAdapter<SlideModel>
{
  private final Context mContext;
  private final LayoutInflater mInflater;
  private final int mResource;
  private final SlideshowModel mSlideshow;
  
  public SlideshowEditActivity$SlideListAdapter(Context paramContext, int paramInt, SlideshowModel paramSlideshowModel)
  {
    super(paramContext, paramInt, paramSlideshowModel);
    mContext = paramContext;
    mResource = paramInt;
    mInflater = LayoutInflater.from(paramContext);
    mSlideshow = paramSlideshowModel;
  }
  
  private View createViewFromResource(int paramInt1, View paramView, int paramInt2)
  {
    paramView = (SlideListItemView)mInflater.inflate(paramInt2, null);
    ((TextView)paramView.findViewById(2131820882)).setText(mContext.getString(2131362019, new Object[] { Integer.valueOf(paramInt1 + 1) }));
    paramInt2 = ((SlideModel)getItem(paramInt1)).getDuration() / 1000;
    ((TextView)paramView.findViewById(2131820883)).setText(mContext.getResources().getQuantityString(2131623937, paramInt2, new Object[] { Integer.valueOf(paramInt2) }));
    SlideshowPresenter localSlideshowPresenter = new SlideshowPresenter(mContext, paramView, mSlideshow);
    ((SlideshowPresenter)localSlideshowPresenter).setLocation(paramInt1);
    localSlideshowPresenter.present();
    return paramView;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return createViewFromResource(paramInt, paramView, mResource);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowEditActivity.SlideListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */