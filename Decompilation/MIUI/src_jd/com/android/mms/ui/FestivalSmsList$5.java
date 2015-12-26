package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;

class FestivalSmsList$5
  implements DialogInterface.OnClickListener
{
  FestivalSmsList$5(FestivalSmsList paramFestivalSmsList, CheckBox paramCheckBox) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (val$check.isChecked()) {
      FestivalFragment.setAllowNetworkingDialog(FestivalSmsList.access$600(this$0), false);
    }
    FestivalFragment.setAllowNetworking(FestivalSmsList.access$600(this$0), true);
    FestivalSmsList.access$700(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalSmsList.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */