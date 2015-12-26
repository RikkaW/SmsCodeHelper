package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

class CustomDatePickerDialog$4
  implements View.OnClickListener
{
  CustomDatePickerDialog$4(CustomDatePickerDialog paramCustomDatePickerDialog, int paramInt1, int paramInt2) {}
  
  public void onClick(View paramView)
  {
    if (CustomDatePickerDialog.access$000(this$0).isLunar()) {
      return;
    }
    CustomDatePickerDialog.access$300(this$0).start();
    CustomDatePickerDialog.access$400(this$0, val$lunarColor, true);
    CustomDatePickerDialog.access$000(this$0).setTextColor(val$lunarColor, val$unSlectColor, val$lunarColor);
    this$0.getButton(-1).setTextColor(val$lunarColor);
    CustomDatePickerDialog.access$000(this$0).setLunar(true);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomDatePickerDialog.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */