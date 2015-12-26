package com.android.mms.ui;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import gr;
import ot;
import zv;

public class NoConfirmationSendService
  extends IntentService
{
  public NoConfirmationSendService()
  {
    super(NoConfirmationSendService.class.getName());
    setIntentRedelivery(true);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    ComposeMessageActivity.b("NoConfirmationSendService onHandleIntent");
    Object localObject = paramIntent.getAction();
    if (!"android.intent.action.RESPOND_VIA_MESSAGE".equals(localObject))
    {
      ComposeMessageActivity.b("NoConfirmationSendService onHandleIntent wrong action: " + (String)localObject);
      return;
    }
    localObject = paramIntent.getExtras();
    if (localObject == null)
    {
      ComposeMessageActivity.b("Called to send SMS but no extras");
      return;
    }
    Log.i("NoConfirmationSendService", "onHandleIntent slotId = " + zv.a(paramIntent, "sim_id"));
    String str1 = ((Bundle)localObject).getString("android.intent.extra.TEXT");
    String str2 = gr.a(paramIntent.getData());
    if (TextUtils.isEmpty(str2))
    {
      ComposeMessageActivity.b("Recipient(s) cannot be empty");
      return;
    }
    if (((Bundle)localObject).getBoolean("showUI", false))
    {
      paramIntent.setClassName(this, "com.android.mms.ui.ComposeMessageActivityNoLockScreen");
      paramIntent.addFlags(268435456);
      startActivity(paramIntent);
      return;
    }
    if (TextUtils.isEmpty(str1))
    {
      ComposeMessageActivity.b("Message cannot be empty");
      return;
    }
    localObject = new ot(this, TextUtils.split(str2, ";"), str1, 0L);
    try
    {
      ((ot)localObject).a(zv.a(paramIntent, "sim_id"));
      ((ot)localObject).a(0L);
      return;
    }
    catch (Exception paramIntent)
    {
      Log.e("Mms/NoConfirmationSendService", "Failed to send SMS message, threadId=" + 0L, paramIntent);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NoConfirmationSendService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */