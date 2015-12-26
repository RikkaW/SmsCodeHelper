package com.xiaomi.mms.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xiaomi.mms.transaction.MxActivateService;

class MxActivateSimpleDialog$1
  implements DialogInterface.OnClickListener
{
  MxActivateSimpleDialog$1(MxActivateSimpleDialog paramMxActivateSimpleDialog) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    MxActivateService.enableAll(MxActivateSimpleDialog.access$000(this$0), true);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxActivateSimpleDialog.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */