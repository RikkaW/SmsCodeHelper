package com.android.mms.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View.MeasureSpec;
import android.view.Window;
import android.view.WindowManager;

public class NewMessagePopupActivity$MessagePopupLayout
  extends SizeAwareLinearLayout
{
  public NewMessagePopupActivity$MessagePopupLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public NewMessagePopupActivity$MessagePopupLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(((Activity)getContext()).getWindow().getWindowManager().getDefaultDisplay().getHeight() / 2, Integer.MIN_VALUE));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessagePopupActivity.MessagePopupLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */