package com.meizu.common.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.widget.CheckBox;

class PermissionDialogBuilder$3
  implements DialogInterface.OnCancelListener
{
  PermissionDialogBuilder$3(PermissionDialogBuilder paramPermissionDialogBuilder) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (PermissionDialogBuilder.access$000(this$0) != null) {
      PermissionDialogBuilder.access$000(this$0).onPerMisssionClick(paramDialogInterface, PermissionDialogBuilder.access$100(this$0).isChecked(), false);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.PermissionDialogBuilder.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */