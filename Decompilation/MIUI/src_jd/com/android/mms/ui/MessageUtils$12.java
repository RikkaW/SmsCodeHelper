package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import miui.os.Build;

final class MessageUtils$12
  implements Runnable
{
  MessageUtils$12(Context paramContext, MessageUtils.UriInfo paramUriInfo) {}
  
  public void run()
  {
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(val$context);
      localBuilder.setMessage(2131362369);
      localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MessageUtils.access$000(val$context, new Intent("android.intent.action.VIEW", val$info.uri));
        }
      });
      localBuilder.setNegativeButton(17039360, null);
      localBuilder.create().show();
      return;
    }
    MessageUtils.access$000(val$context, new Intent("android.intent.action.VIEW", val$info.uri));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */