package com.android.mms.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvancedValueListPreference
  extends ValueListPreference
{
  private GetSimInfo mSimInfo;
  private int mSlotId = 0;
  
  public AdvancedValueListPreference(Context paramContext)
  {
    super(paramContext);
  }
  
  public AdvancedValueListPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public int getSlotId()
  {
    return mSlotId;
  }
  
  public void init(Context paramContext, int paramInt)
  {
    mSimInfo = ((GetSimInfo)paramContext);
    mSlotId = paramInt;
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    TextView localTextView2 = (TextView)paramView.findViewById(16908310);
    TextView localTextView1 = (TextView)paramView.findViewById(16908304);
    paramView = (ImageView)paramView.findViewById(16908294);
    if (paramView != null)
    {
      paramView.setMinimumWidth(0);
      int i = mSimInfo.getSimDisplayIcon(mSlotId);
      if (i == 0) {
        break label125;
      }
      paramView.setBackgroundResource(i);
      paramView.setVisibility(0);
    }
    for (;;)
    {
      localTextView2.setVisibility(0);
      localTextView2.setText(mSimInfo.getSimDisplayName(mSlotId));
      paramView = mSimInfo.getSimNumber(mSlotId);
      if (!TextUtils.isEmpty(paramView)) {
        break;
      }
      localTextView1.setVisibility(8);
      return;
      label125:
      paramView.setVisibility(8);
    }
    localTextView1.setText(paramView);
    localTextView1.setVisibility(0);
  }
  
  public void setNotifyChanged(Context paramContext, int paramInt)
  {
    init(paramContext, paramInt);
    notifyChanged();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AdvancedValueListPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */