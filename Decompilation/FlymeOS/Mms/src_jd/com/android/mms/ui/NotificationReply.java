package com.android.mms.ui;

import aaa;
import aau;
import aba;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import gm;
import gq;
import gr;
import ny;
import ot;
import ph;
import wd;
import wy;
import wz;
import xa;
import xb;
import xc;
import xd;
import zv;

public class NotificationReply
  extends Activity
{
  private ImageView a;
  private TextView b;
  private TextView c;
  private EditText d;
  private View e;
  private ImageView f;
  private View g;
  private Context h;
  private gm i;
  private boolean j = false;
  private int k;
  private String l;
  private long m;
  private final BroadcastReceiver n = new xc(this);
  
  private void a()
  {
    a = ((ImageView)findViewById(2131886621));
    b = ((TextView)findViewById(2131886623));
    Typeface localTypeface = Typeface.createFromFile("/system/fonts/Roboto-Regular.ttf");
    b.setTypeface(localTypeface);
    c = ((TextView)findViewById(2131886625));
    f = ((ImageView)findViewById(2131886624));
    d = ((EditText)findViewById(2131886617));
    d.setMaxHeight(getResources().getDimensionPixelSize(2131559758));
    d.addTextChangedListener(new wy(this));
    e = findViewById(2131886618);
    e.setEnabled(false);
    findViewById(2131886704).setOnClickListener(new wz(this));
    g = findViewById(2131886702);
    g.addOnAttachStateChangeListener(new xa(this));
  }
  
  private void a(long paramLong, String paramString)
  {
    if ((paramLong <= 0L) || (paramString == null) || (TextUtils.getTrimmedLength(paramString) <= 0)) {
      return;
    }
    new Thread(new xd(this, paramLong, paramString), "NotificationReply.asyncUpdateDraftSmsMessage").start();
    wd.a(2131493008, h, 0, 0, true, 1);
  }
  
  private void a(Intent paramIntent)
  {
    MessagingNotification.b(this, 1);
    l = paramIntent.getStringExtra("smsMessageBody");
    m = paramIntent.getLongExtra("smsThreadId", -1L);
    k = paramIntent.getIntExtra("smsSlotId", -1);
  }
  
  private void a(String paramString, long paramLong, int paramInt)
  {
    if (i == null) {
      return;
    }
    String[] arrayOfString = TextUtils.split(i.d(), ";");
    if (c()) {
      a(paramString, arrayOfString, paramLong);
    }
    for (;;)
    {
      d.setText("");
      finish();
      return;
      a(paramString, arrayOfString, paramLong, paramInt);
    }
  }
  
  private void a(String paramString, String[] paramArrayOfString, long paramLong)
  {
    paramString = new ph(this, paramArrayOfString, paramString, paramLong, -1);
    try
    {
      paramString.a(paramLong);
      return;
    }
    catch (Exception paramString)
    {
      Log.i("NotificationReply", "Failed to send SipSms message, threadId = " + paramLong + ", e = " + paramString);
    }
  }
  
  private void a(String paramString, String[] paramArrayOfString, long paramLong, int paramInt)
  {
    Log.i("NotificationReply", "sendSmsWorker slotId = " + paramInt);
    paramString = new ot(this, paramArrayOfString, paramString, paramLong);
    paramString.a(paramInt);
    try
    {
      paramString.a(paramLong);
      return;
    }
    catch (Exception paramString)
    {
      Log.i("NotificationReply", "Failed to send Sms message, threadId = " + paramLong + ", e = " + paramString);
    }
  }
  
  private void a(boolean paramBoolean)
  {
    j = paramBoolean;
    if (paramBoolean)
    {
      d.setHint(2131493612);
      f.setVisibility(0);
      f.setImageResource(2130837569);
      return;
    }
    d.setHint(2131493613);
    paramBoolean = i.d().equals("4007883333");
    zv.a(f, 6, k, paramBoolean);
  }
  
  private void b()
  {
    gq localgq = gr.a(MmsApp.c(), m, false).h();
    if (localgq.size() == 0) {
      return;
    }
    i = ((gm)localgq.get(0));
    a(i.d());
    a.setImageBitmap(aaa.a(h, localgq, aaa.a, aaa.b));
    b.setText(i.g());
    c.setText(l);
    e.setOnClickListener(new xb(this));
  }
  
  private boolean b(String paramString)
  {
    return (paramString != null) && (TextUtils.getTrimmedLength(paramString) > 0) && (!wd.a(paramString));
  }
  
  private boolean c()
  {
    return j;
  }
  
  private void d()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.android.mms.sip.dest_check_popup");
    localIntentFilter.addDataScheme("content");
    registerReceiver(n, localIntentFilter);
  }
  
  public void a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      Log.i("NotificationReply", "checkSipDestAddrIsAvailable numbers is null");
    }
    do
    {
      return;
      Log.i("NotificationReply", "checkSipDestAddrIsAvailable number is " + paramString + ", isSipOffLine = " + aba.a().c());
      if (aba.a().c())
      {
        j = false;
        a(j);
        return;
      }
    } while (!aba.a().b());
    aba.a().a(this, new String[] { paramString });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968808);
    h = this;
    d();
    a();
    if (paramBundle != null)
    {
      l = paramBundle.getString("smsMessageBody");
      m = paramBundle.getLong("smsThreadId", -1L);
      k = paramBundle.getInt("smsSlotId", -1);
    }
    for (;;)
    {
      b();
      return;
      a(getIntent());
    }
  }
  
  protected void onDestroy()
  {
    unregisterReceiver(n);
    aau.b(View.class, g, "blurUnderMeRect", Boolean.TYPE, Boolean.valueOf(false));
    super.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    long l1 = paramIntent.getLongExtra("smsThreadId", -1L);
    if (l1 != m) {
      d.setText("");
    }
    Log.i("NotificationReply", "onNewIntent   newThreadId:" + l1 + "   oldThreadId" + m);
    a(paramIntent);
    b();
  }
  
  protected void onPause()
  {
    super.onPause();
    a(m, d.getText().toString());
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("smsMessageBody", l);
    paramBundle.putLong("smsThreadId", m);
    paramBundle.putInt("smsSlotId", k);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NotificationReply
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */