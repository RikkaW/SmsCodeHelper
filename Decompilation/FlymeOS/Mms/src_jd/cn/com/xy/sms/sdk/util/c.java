package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import java.util.Map;

final class c
  extends AbsSdkDoAction
{
  public final void deleteMsgForDatabase(Context paramContext, String paramString) {}
  
  public final String getContactName(Context paramContext, String paramString)
  {
    return null;
  }
  
  public final void markAsReadForDatabase(Context paramContext, String paramString) {}
  
  public final void openSms(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("sms:" + paramString)));
  }
  
  public final void openSmsDetail(Context paramContext, String paramString, Map paramMap) {}
  
  public final void sendSms(Context paramContext, String paramString1, String paramString2, int paramInt, Map<String, String> paramMap)
  {
    try
    {
      paramString1 = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + paramString1));
      paramString1.putExtra("sms_body", paramString2);
      paramContext.startActivity(paramString1);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */