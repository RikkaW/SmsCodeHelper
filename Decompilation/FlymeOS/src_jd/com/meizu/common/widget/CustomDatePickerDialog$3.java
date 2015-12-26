package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.widget.TextView;

class CustomDatePickerDialog$3
  implements ValueAnimator.AnimatorUpdateListener
{
  CustomDatePickerDialog$3(CustomDatePickerDialog paramCustomDatePickerDialog, ValueAnimator paramValueAnimator, boolean paramBoolean, int paramInt) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    int i = Color.rgb(((Integer)val$colorAnimation.getAnimatedValue("RgbRed")).intValue(), ((Integer)val$colorAnimation.getAnimatedValue("RgbGreen")).intValue(), ((Integer)val$colorAnimation.getAnimatedValue("RgbBlue")).intValue());
    if (val$isLunar)
    {
      CustomDatePickerDialog.access$100(this$0).setTextColor(i);
      CustomDatePickerDialog.access$200(this$0).setTextColor(val$unselectedTabColor + this$0.tabTextSelectColor - i);
      return;
    }
    CustomDatePickerDialog.access$100(this$0).setTextColor(val$unselectedTabColor + this$0.tabTextSelectColor - i);
    CustomDatePickerDialog.access$200(this$0).setTextColor(i);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomDatePickerDialog.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */