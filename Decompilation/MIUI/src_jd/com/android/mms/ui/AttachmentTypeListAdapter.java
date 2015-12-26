package com.android.mms.ui;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;

public class AttachmentTypeListAdapter
  extends BaseAdapter
{
  public static AttachmentTypeItem[] mTypeItems = { new AttachmentTypeItem(2131362045, 2130837687, true), new AttachmentTypeItem(2131362046, 2130837661, true), new AttachmentTypeItem(2131362047, 2130837669, false), new AttachmentTypeItem(2131361975, 2130837673, true), new AttachmentTypeItem(2131361976, 2130837699, true), new AttachmentTypeItem(2131362048, 2130837665, true), new AttachmentTypeItem(2131362091, 2130837677, true), new AttachmentTypeItem(2131362148, 2130837703, true), new AttachmentTypeItem(2131362133, 2130837695, true), new AttachmentTypeItem(2131361979, 2130837691, true), new AttachmentTypeItem(2131361977, 2130837707, true), new AttachmentTypeItem(2131361981, 2130837682, true) };
  private Context mContext;
  private LayoutInflater mInflater;
  
  public AttachmentTypeListAdapter(Context paramContext)
  {
    mContext = paramContext;
    mInflater = ((LayoutInflater)mContext.getSystemService("layout_inflater"));
  }
  
  public int getCount()
  {
    return mTypeItems.length;
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null) {}
    for (;;)
    {
      paramViewGroup = (TextView)paramView.findViewById(2131820548);
      ImageView localImageView = (ImageView)paramView.findViewById(2131820547);
      paramViewGroup.setText(mTypeItemsnameRes);
      localImageView.setImageDrawable(mTypeItemsiconRes);
      return paramView;
      paramView = mInflater.inflate(2130968578, paramViewGroup, false);
    }
  }
  
  public View inflateEmptyView()
  {
    return mInflater.inflate(2130968578, null, false);
  }
  
  public static class AttachmentTypeItem
  {
    public Drawable iconRes;
    public boolean jump;
    public int nameRes;
    
    public AttachmentTypeItem(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      nameRes = paramInt1;
      iconRes = MmsApp.getApp().getResources().getDrawable(paramInt2);
      jump = paramBoolean;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentTypeListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */