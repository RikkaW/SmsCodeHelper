package com.meizu.common.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;

class PermissionDialogBuilder$1
  implements DialogInterface.OnClickListener
{
  PermissionDialogBuilder$1(PermissionDialogBuilder paramPermissionDialogBuilder) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (PermissionDialogBuilder.access$000(this$0) != null) {
      PermissionDialogBuilder.access$000(this$0).onPerMisssionClick(paramDialogInterface, PermissionDialogBuilder.access$100(this$0).isChecked(), true);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.PermissionDialogBuilder.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */