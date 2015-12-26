package com.xiaomi.channel.commonutils.logger;

import android.util.Log;

public class DefaultAndroidLogger
  implements LoggerInterface
{
  private String mTag = "xiaomi";
  
  public void log(String paramString)
  {
    Log.v(mTag, paramString);
  }
  
  public void log(String paramString, Throwable paramThrowable)
  {
    Log.v(mTag, paramString, paramThrowable);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.logger.DefaultAndroidLogger
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */