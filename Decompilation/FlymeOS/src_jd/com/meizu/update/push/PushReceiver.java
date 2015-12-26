package com.meizu.update.push;

import ake;
import akp;
import android.content.Context;
import android.text.TextUtils;
import anf;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;

public class PushReceiver
  extends MzPushMessageReceiver
{
  public void onMessage(Context paramContext, String paramString)
  {
    if (!akp.a(paramContext, paramString)) {}
  }
  
  public void onRegister(Context paramContext, String paramString)
  {
    anf.c("onRegister : " + paramString);
    if (!TextUtils.isEmpty(paramString)) {
      ake.a(paramContext);
    }
  }
  
  public void onUnRegister(Context paramContext, boolean paramBoolean)
  {
    anf.d("onUnRegister");
  }
}

/* Location:
 * Qualified Name:     com.meizu.update.push.PushReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */