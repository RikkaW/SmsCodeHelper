package com.meizu.common.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.widget.Button;

class CustomDatePickerDialog$2
  implements DialogInterface.OnShowListener
{
  CustomDatePickerDialog$2(CustomDatePickerDialog paramCustomDatePickerDialog) {}
  
  public void onShow(DialogInterface paramDialogInterface)
  {
    if ((CustomDatePickerDialog.access$000(this$0) != null) && (CustomDatePickerDialog.access$000(this$0).isLunar()))
    {
      this$0.getButton(-1).setTextColor(this$0.lunarColor);
      return;
    }
    this$0.getButton(-1).setTextColor(this$0.gregorianColor);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomDatePickerDialog.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */