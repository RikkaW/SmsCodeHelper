package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

class CustomDatePickerDialog$5
  implements View.OnClickListener
{
  CustomDatePickerDialog$5(CustomDatePickerDialog paramCustomDatePickerDialog, int paramInt1, int paramInt2) {}
  
  public void onClick(View paramView)
  {
    if (!CustomDatePickerDialog.access$000(this$0).isLunar()) {
      return;
    }
    CustomDatePickerDialog.access$300(this$0).start();
    CustomDatePickerDialog.access$400(this$0, val$gregorianColor, false);
    CustomDatePickerDialog.access$000(this$0).setTextColor(val$gregorianColor, val$unSlectColor, val$gregorianColor);
    this$0.getButton(-1).setTextColor(val$gregorianColor);
    CustomDatePickerDialog.access$000(this$0).setLunar(false);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomDatePickerDialog.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */