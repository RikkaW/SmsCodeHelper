package com.android.mms.ui;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvancedCheckBoxPreference
  extends CheckBoxPreference
{
  private GetSimInfo mSimInfo;
  private int mSlotId = 0;
  
  public AdvancedCheckBoxPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AdvancedCheckBoxPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setLayoutResource(2130968576);
  }
  
  public void init(Context paramContext, int paramInt)
  {
    mSimInfo = ((GetSimInfo)paramContext);
    mSlotId = paramInt;
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    TextView localTextView2 = (TextView)paramView.findViewById(2131820545);
    TextView localTextView1 = (TextView)paramView.findViewById(2131820546);
    paramView = (ImageView)paramView.findViewById(2131820544);
    if (paramView != null)
    {
      int i = mSimInfo.getSimDisplayIcon(mSlotId);
      if (i <= 0) {
        break label114;
      }
      paramView.setBackgroundResource(i);
      paramView.setVisibility(0);
    }
    for (;;)
    {
      localTextView2.setText(mSimInfo.getSimDisplayName(mSlotId));
      paramView = mSimInfo.getSimNumber(mSlotId);
      if (!TextUtils.isEmpty(paramView)) {
        break;
      }
      localTextView1.setVisibility(8);
      return;
      label114:
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
 * Qualified Name:     com.android.mms.ui.AdvancedCheckBoxPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */