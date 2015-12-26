package com.android.mms.ui;

import aau;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Telephony.MmsSms;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import uh;
import ui;
import uj;

public class DialogFromNotifyActivity
  extends Activity
{
  private static final int a = "         ".length() * 2;
  private SmsMessage b = null;
  private Uri c = null;
  private boolean d = false;
  private long e = 0L;
  private AlertDialog f = null;
  private int g = 0;
  private String h = "";
  private Handler i = new uh(this);
  private final DialogInterface.OnClickListener j = new ui(this);
  private final DialogInterface.OnClickListener k = new uj(this);
  
  private void a(Intent paramIntent)
  {
    if ((f != null) && (f.isShowing())) {
      f.dismiss();
    }
    Intent localIntent = paramIntent;
    if (paramIntent == null) {
      localIntent = getIntent();
    }
    h = localIntent.getStringExtra("MsgSelection");
    g = localIntent.getIntExtra("Actiontype", 0);
    paramIntent = localIntent.getStringExtra("ActionMessage");
    if (TextUtils.isEmpty(h))
    {
      finish();
      return;
    }
    aau.a("com.android.internal.statusbar.IStatusBarService$Stub", (String)aau.a("android.content.Context", "STATUS_BAR_SERVICE"), "collapsePanels");
    long l = SystemClock.uptimeMillis();
    f = new AlertDialog.Builder(this).setMessage(paramIntent).setPositiveButton(2131493166, k).setNegativeButton(2131493022, j).create();
    f.getWindow().setType(2010);
    f.show();
    e = l;
    Log.e("DialogFromNotifyActivity", "displayNotificationDialog");
  }
  
  private void a(String paramString)
  {
    new StringBuilder();
    Uri localUri = Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "messages_group_delete");
    getContentResolver().delete(localUri, null, new String[] { paramString });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setBackgroundDrawableResource(2130838693);
    i.sendEmptyMessageAtTime(2, 100L);
  }
  
  protected void onDestroy()
  {
    if ((f != null) && (f.isShowing())) {
      f.dismiss();
    }
    super.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    a(paramIntent);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putLong("timer_fire", e);
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
    Log.e("DialogFromNotifyActivity", "onStop");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.DialogFromNotifyActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */