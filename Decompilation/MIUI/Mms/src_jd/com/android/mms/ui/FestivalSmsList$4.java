package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;

class FestivalSmsList$4
  implements DialogInterface.OnClickListener
{
  FestivalSmsList$4(FestivalSmsList paramFestivalSmsList, CheckBox paramCheckBox) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (val$check.isChecked()) {
      FestivalFragment.setAllowNetworkingDialog(FestivalSmsList.access$600(this$0), false);
    }
    FestivalFragment.setAllowNetworking(FestivalSmsList.access$600(this$0), false);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalSmsList.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */