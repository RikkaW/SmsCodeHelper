package com.xiaomi.mms.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class MxActivateSimpleDialog$6
  implements DialogInterface.OnCancelListener
{
  MxActivateSimpleDialog$6(MxActivateSimpleDialog paramMxActivateSimpleDialog) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (MxActivateSimpleDialog.access$300(this$0) != null) {
      MxActivateSimpleDialog.access$300(this$0).onResult(1);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxActivateSimpleDialog.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */