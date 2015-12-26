package com.meizu.common.app;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.CheckBox;

public class PermissionDialogBuilder$HideAlertDialog
  extends AlertDialog
{
  protected PermissionDialogBuilder$HideAlertDialog(PermissionDialogBuilder paramPermissionDialogBuilder, Context paramContext)
  {
    super(paramContext);
  }
  
  public void show()
  {
    if (PermissionDialogBuilder.access$000(this$0) != null) {
      PermissionDialogBuilder.access$000(this$0).onPerMisssionClick(this, PermissionDialogBuilder.access$100(this$0).isChecked(), true);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.PermissionDialogBuilder.HideAlertDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */