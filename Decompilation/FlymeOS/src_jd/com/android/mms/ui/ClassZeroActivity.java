package com.android.mms.ui;

import aau;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import com.android.mms.transaction.MessagingNotification;
import fy;
import java.util.ArrayList;
import pz;
import qa;

public class ClassZeroActivity
  extends fy
{
  private static final int a = "         ".length() * 2;
  private static final String[] b = { "_id", "address", "protocol" };
  private SmsMessage c = null;
  private Uri d = null;
  private boolean e = false;
  private long f = 0L;
  private AlertDialog g = null;
  private ArrayList<SmsMessage> h = null;
  private Handler i = new pz(this);
  private final DialogInterface.OnClickListener j = new qa(this);
  
  private void a(SmsMessage paramSmsMessage)
  {
    if ((g != null) && (g.isShowing())) {
      g.dismiss();
    }
    paramSmsMessage = paramSmsMessage.getMessageBody();
    long l = SystemClock.uptimeMillis();
    g = new AlertDialog.Builder(this).setMessage(paramSmsMessage).setNegativeButton(2131493166, j).setCancelable(false).create();
    g.show();
    f = l;
    Log.e("display_00", "displayClassZeroMessage");
  }
  
  private boolean a(Intent paramIntent)
  {
    Intent localIntent = paramIntent;
    if (paramIntent == null) {
      localIntent = getIntent();
    }
    paramIntent = localIntent.getByteArrayExtra("pdu");
    String str = localIntent.getStringExtra("format");
    d = localIntent.getData();
    paramIntent = aau.a(SmsMessage.class, "createFromPdu", new Class[] { byte[].class, String.class }, new Object[] { paramIntent, str });
    if (paramIntent != null)
    {
      c = ((SmsMessage)paramIntent);
      if (TextUtils.isEmpty(c.getMessageBody().toString()))
      {
        if (h.size() == 0) {
          finish();
        }
        return false;
      }
    }
    else
    {
      Log.i("ClassZeroActivity", "queueMsgFromIntent mMessage is null");
      return false;
    }
    h.add(c);
    return true;
  }
  
  private void b()
  {
    int m = 1;
    ContentValues localContentValues;
    if (d != null)
    {
      localContentValues = new ContentValues();
      if (!e) {
        break label75;
      }
      k = 1;
      localContentValues.put("read", Integer.valueOf(k));
      if (!e) {
        break label80;
      }
    }
    label75:
    label80:
    for (int k = m;; k = 0)
    {
      localContentValues.put("seen", Integer.valueOf(k));
      getContentResolver().update(d, localContentValues, null, null);
      MessagingNotification.c(this);
      return;
      k = 0;
      break;
    }
  }
  
  private void c()
  {
    if (h.size() > 0) {
      h.remove(0);
    }
    if (h.size() == 0)
    {
      finish();
      return;
    }
    a((SmsMessage)h.get(0));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setBackgroundDrawableResource(2130838693);
    getWindow().setFlags(131072, 131080);
    if (h == null) {
      h = new ArrayList();
    }
    i.sendEmptyMessageAtTime(2, 100L);
  }
  
  protected void onDestroy()
  {
    if ((g != null) && (g.isShowing())) {
      g.dismiss();
    }
    super.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    e = true;
    b();
    e = false;
    a(paramIntent);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putLong("timer_fire", f);
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
    Log.e("display_00", "onStop");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ClassZeroActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */