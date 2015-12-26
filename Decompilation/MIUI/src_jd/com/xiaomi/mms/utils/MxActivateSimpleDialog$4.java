package com.xiaomi.mms.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.activate.ActivateManager;

class MxActivateSimpleDialog$4
  implements DialogInterface.OnClickListener
{
  MxActivateSimpleDialog$4(MxActivateSimpleDialog paramMxActivateSimpleDialog) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    int i = MSimUtils.getMultiSimCount();
    if (MxActivateSimpleDialog.access$100(this$0) != -1)
    {
      i = MxActivateSimpleDialog.access$100(this$0);
      paramInt = i;
      paramDialogInterface = ActivateManager.get(MxActivateSimpleDialog.access$000(this$0));
      label36:
      if (paramInt > i) {
        break label88;
      }
      if (!MxActivateSimpleDialog.access$200(this$0)) {
        break label82;
      }
    }
    label82:
    for (int j = 17;; j = 1)
    {
      paramDialogInterface.startActivateSim(paramInt, 2, null, false, null, j);
      paramInt += 1;
      break label36;
      paramInt = 0;
      i -= 1;
      break;
    }
    label88:
    if (MxActivateSimpleDialog.access$300(this$0) != null) {
      MxActivateSimpleDialog.access$300(this$0).onResult(0);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxActivateSimpleDialog.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */