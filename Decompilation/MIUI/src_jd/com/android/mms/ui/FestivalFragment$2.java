package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;

class FestivalFragment$2
  implements DialogInterface.OnClickListener
{
  FestivalFragment$2(FestivalFragment paramFestivalFragment, CheckBox paramCheckBox) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (val$check.isChecked()) {
      FestivalFragment.setAllowNetworkingDialog(FestivalFragment.access$300(this$0), false);
    }
    FestivalFragment.setAllowNetworking(FestivalFragment.access$300(this$0), false);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalFragment.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */