package cn.com.xy.sms.sdk.action;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

final class a
{
  public static String a(String paramString)
  {
    paramString = new FutureTask(new b(paramString));
    new Thread(paramString).start();
    return (String)paramString.get(5000L, TimeUnit.MILLISECONDS);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.action.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */