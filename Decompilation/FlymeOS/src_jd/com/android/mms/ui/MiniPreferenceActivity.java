package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import wv;
import ww;

public class MiniPreferenceActivity
  extends Activity
{
  public static String a = "com.android.mms.intent.action.MESSAGING_APP_NOTIFICATIONS";
  private DialogInterface.OnClickListener b = new ww(this);
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (!MessagingPreferenceActivity.a(this))
    {
      setResult(-1);
      finish();
    }
    new AlertDialog.Builder(this).setMessage(getResources().getString(2131492938)).setCancelable(true).setPositiveButton(2131493166, b).setNegativeButton(2131493022, b).show().setOnDismissListener(new wv(this));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MiniPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */