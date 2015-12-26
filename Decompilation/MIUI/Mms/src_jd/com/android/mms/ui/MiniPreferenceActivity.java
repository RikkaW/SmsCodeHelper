package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.Resources;
import android.os.Bundle;

public class MiniPreferenceActivity
  extends Activity
{
  public static String DISABLE_NOTIFICATIONS_INTENT = "com.android.mms.intent.action.MESSAGING_APP_NOTIFICATIONS";
  private DialogInterface.OnClickListener mDialogButtonListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      if (paramAnonymousInt == -1)
      {
        MessagingPreferenceActivity.enableNotifications(false, MiniPreferenceActivity.this);
        setResult(-1);
      }
      for (;;)
      {
        finish();
        return;
        setResult(0);
      }
    }
  };
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (!MessagingPreferenceActivity.getNotificationEnabled(this))
    {
      setResult(-1);
      finish();
    }
    new AlertDialog.Builder(this).setMessage(getResources().getString(2131362041)).setCancelable(true).setPositiveButton(2131361891, mDialogButtonListener).setNegativeButton(2131361892, mDialogButtonListener).show().setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (!isFinishing()) {
          finish();
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MiniPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */